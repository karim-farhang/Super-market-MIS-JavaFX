package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditeCustomer {

    @FXML
    private JFXTextField txt_name;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private JFXTextField txt_number;

    @FXML
    private JFXTextField txt_number2;
    @FXML
    private JFXTextField txt_rag_date;

    @FXML
    void Close(ActionEvent event) {
        Helper.edite_customer.close();
    }

    @FXML
    void OK(ActionEvent event) {

        try {


            String name = txt_name.getText();
            String address = txt_address.getText();
            String number = txt_number.getText();
            String number2 = txt_number2.getText();
            String rag_date = txt_rag_date.getText();

            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("SELECT cus_id FROM Customer WHERE cus_name='" + AddNewCustomer.name + "';");

            rt.next();
            int CU_ID = rt.getInt("cus_id");
            st = Database.getInstance().createStatement();

            int up = st.executeUpdate("UPDATE Customer set cus_name='" + name + "',cus_address = '" + address + "',cus_number='" + number + "',cus_number2='" + number2 + "',cus_rag_date='" + rag_date + "' WHERE cus_id=" + CU_ID + ";");

            if (up == 1) {
                Helper.masseg("یک مورد بروز رسانی شود !", Alert.AlertType.INFORMATION);
            }
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void initialize() {
        txt_name.setText(AddNewCustomer.name);
        txt_address.setText(AddNewCustomer.address);
        txt_number.setText(AddNewCustomer.number);
        txt_number2.setText(AddNewCustomer.number2);
        txt_rag_date.setText(AddNewCustomer.rag_date);
    }

    @FXML
    public void MouseDragged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    public void MousePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

}
