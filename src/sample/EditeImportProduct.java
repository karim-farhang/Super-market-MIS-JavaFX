package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditeImportProduct {

    int p_id = 0;
    @FXML
    private JFXTextField txt_name;
    @FXML
    private JFXComboBox<String> comb_catagory;
    @FXML
    private JFXTextField txt_total;
    @FXML
    private JFXComboBox<String> comb_unit;
    @FXML
    private JFXTextField txt_price;
    @FXML
    private JFXTextField txt_import_date;
    @FXML
    private JFXTextField txt_expire_date;
    @FXML
    private JFXComboBox<String> comb_sell_name;
    @FXML
    private JFXButton btn_close;
    @FXML
    private JFXButton btn_ok;

    @FXML
    void MouseDeagged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void MousePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    void Mouse_Dragged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void Mouse_pressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    void btn_close_onAction(ActionEvent event) {
        Helper.edite_import_product.close();
    }

    @FXML
    void btn_on_onAction(ActionEvent event) {
        try {
            String Catagory = comb_catagory.getSelectionModel().getSelectedItem();
            String Unit = comb_unit.getSelectionModel().getSelectedItem();
            String Seller = comb_sell_name.getSelectionModel().getSelectedItem();
            Statement st = Database.getInstance().createStatement();

            ResultSet rt = st.executeQuery("SELECT cat_id FROM catagory WHERE cat_name='" + Catagory + "';");

            rt.next();
            String c_id = rt.getString("cat_id");

            st = Database.getInstance().createStatement();

            rt = st.executeQuery("SELECT uni_id FROM units WHERE uni_name='" + Unit + "';");

            rt.next();
            String u_id = rt.getString("uni_id");

            st = Database.getInstance().createStatement();

            rt = st.executeQuery("SELECT sel_id FROM seller WHERE sel_name='" + Seller + "';");

            rt.next();
            String ce_id = rt.getString("sel_id");

            String name = txt_name.getText();
            String price = txt_price.getText();
            String total = txt_total.getText();
            String imp_d = txt_import_date.getText();
            String exp_d = txt_expire_date.getText();

            st = Database.getInstance().createStatement();
            int up = st.executeUpdate("update product set pro_name ='" + name + "', pro_total=" + total + " , FK_catgory_id=" + c_id + " , FK_unit_id = " + u_id + ", pro_price=" + price + " , pro_import_date='" + imp_d + "', pro_expire_date = '" + exp_d + "' , FK_seller_id = " + ce_id + " where pro_id = " + p_id + ";");

            if (up == 1) {
                Helper.masseg("یک مورد بروزرسانی شود !", Alert.AlertType.INFORMATION);
            }
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    void initialize() {
        try {

            comb_catagory.getItems().setAll(ImportProduct.type);
            comb_unit.getItems().setAll(ImportProduct.units);
            comb_sell_name.getItems().setAll(ImportProduct.seller);
            txt_name.setText(ImportProduct.nameT);
            comb_catagory.getSelectionModel().select(ImportProduct.catagoryT);
            txt_total.setText(ImportProduct.totalT);
            comb_unit.getSelectionModel().select(ImportProduct.unitT);
            txt_price.setText(ImportProduct.priceT);
            txt_import_date.setText(ImportProduct.i_dateT);
            txt_expire_date.setText(ImportProduct.e_dateT);
            comb_sell_name.getSelectionModel().select(ImportProduct.selerT);

            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("SELECT pro_id FROM product WHERE pro_name='" + ImportProduct.nameT + "';");
            rt.next();
            p_id = rt.getInt("pro_id");
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }

    }
}
