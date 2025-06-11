package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrintBill {
    ArrayList<String> customer;
    @FXML
    private JFXTextArea txtArea;
    @FXML
    private ComboBox<String> comb_customer_name;
    @FXML
    private Label total_bught;
    @FXML
    private JFXTextField pay_mony;
    @FXML
    private Label rminder;
    @FXML
    private JFXTextField date;

    @FXML
    void MouseDragged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void MousePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    void btn_save_to_file(ActionEvent event) {
        try {
            FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("*.txt", "*.TXT");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(filter1);
            fileChooser.setInitialFileName(comb_customer_name.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.println(txtArea.getText());
                printWriter.close();
            }
        } catch (IOException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void initialize() {
        customer = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = Database.getInstance().prepareStatement("SELECT cus_name FROM TableBill WHERE fac_id > ? ;");
            preparedStatement.setInt(1, SellProduct.LastSell);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer.add(resultSet.getString("cus_name"));
            }
            comb_customer_name.getItems().setAll(customer);
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    public void btn_Clos(ActionEvent actionEvent) {
        SellProduct.printbill.close();
    }

    @FXML
    public void Comb_onAction(ActionEvent actionEvent) {

        try {
            String cus_name = comb_customer_name.getSelectionModel().getSelectedItem();
            PreparedStatement st = Database.getInstance().prepareStatement("SELECT * FROM SellProduct WHERE fac_id > ? AND cus_name = ? ;");
            st.setInt(1, SellProduct.LastSell);
            st.setString(2, cus_name);
            ResultSet res = st.executeQuery();


            double totoalBay = 0;
            this.txtArea.setText("\n" + "اجناس" + "               " + "تعداد" + "                 " + "واحد" + "                 " + "فیمت" + "                 " + "فیمت مچموعه");
            while (res.next()) {

                String pro = res.getString("pro_name");
                double tot = res.getDouble("fac_total");
                String uni = res.getString("uni_name");
                double pri = res.getDouble("pro_price");
                double p_t = pri * tot;
                totoalBay += p_t;
                this.txtArea.setText(txtArea.getText() + "\n" + pro + "                  " + tot + "                " + uni + "              " + pri + "            " + p_t);
            }
            total_bught.setText(totoalBay + "");

            PreparedStatement prepS = Database.getInstance().prepareStatement("SELECT FK_cus_id,fac_id FROM factor,bill WHERE factor.fac_id = bill.f_id  and fac_id > ? ");
            prepS.setInt(1, SellProduct.LastSell);
            ResultSet resultSet = prepS.executeQuery();
            resultSet.next();
            if (resultSet.getRow() == 1) {

            } else {
                //     PreparedStatement statement = Database.getInstance().prepareStatement("INSERT INTO bill (bil_id,)")
            }


        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }
}
