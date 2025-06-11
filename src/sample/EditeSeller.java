package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditeSeller {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField number;
    @FXML
    private JFXTextField number2;
    @FXML
    private JFXTextField date;

    @FXML
    void close_onAction(ActionEvent event) {
        Helper.edite_seller.close();
    }

    @FXML
    void ok_onAction(ActionEvent event) {

        int id = 0;
        String n, ad, nu, nu2, da = null;
        try {

            n = name.getText();
            ad = address.getText();
            nu = number.getText();
            nu2 = number2.getText();
            da = date.getText();

            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("SELECT sel_id FROM Seller WHERE sel_name='" + n + "';");
            rt.next();
            id = rt.getInt("sel_id");
            int update = st.executeUpdate("UPDATE Seller SET sel_name ='" + n + "', sel_address='" + ad + "', sel_number = '" + nu + "', sel_number2 ='" + nu2 + "', sel_rag_date='" + da + "' WHERE sel_id =" + id + ";");
            if (update == 1) {
                Helper.masseg("یک مورد بروز رسانی شود!", Alert.AlertType.INFORMATION);
            }
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void onMoueDragged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void onMousePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    void initialize() {
        name.setText(AddNewSeller.edite_name);
        address.setText(AddNewSeller.edite_address);
        number.setText(AddNewSeller.edite_number);
        number2.setText(AddNewSeller.edite_number2);
        date.setText(AddNewSeller.edite_date);
    }
}
