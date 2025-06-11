package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PayMonySeller {
    ObservableList<TablePaySellerMony> product = FXCollections.observableArrayList();
    Helper helper = new Helper();
    int Seller_ID = 0;
    int R_ID = 0;
    double total_mony = 0;
    double reminder = 0;
    @FXML
    private TableView<TablePaySellerMony> table;
    @FXML
    private TableColumn<TablePaySellerMony, String> col_product;
    @FXML
    private TableColumn<TablePaySellerMony, String> col_total;
    @FXML
    private TableColumn<TablePaySellerMony, String> col_unit;
    @FXML
    private TableColumn<TablePaySellerMony, String> col_price;
    @FXML
    private TableColumn<TablePaySellerMony, String> col_import_date;
    @FXML
    private JFXComboBox<String> comb_seller;
    @FXML
    private Label txt_total_mony;
    @FXML
    private Label txt_reminder;
    @FXML
    private JFXTextField txt_pay;
    @FXML
    private JFXTextField txt_pay_date;

    @FXML
    void initialize() {
        comb_seller.getItems().setAll(ImportProduct.seller);
    }

    //1
    private void RefreshTable(String seller_name) throws ClassNotFoundException, SQLException {
        product.clear();
        total_mony = 0;

        Statement st = Database.getInstance().createStatement();
        ResultSet rt = st.executeQuery("SELECT sel_id from seller where sel_name='" + seller_name + "'");
        rt.next();
        Seller_ID = rt.getInt("sel_id");
        st = Database.getInstance().createStatement();
        rt = st.executeQuery("SELECT pro_id,pro_name,pro_total,uni_name,pro_price,pro_import_date FROM product,units WHERE units.uni_id=product.FK_unit_id AND FK_seller_id=" + Seller_ID + ";");

        while (rt.next()) {
            product.add(new TablePaySellerMony(
                    rt.getInt("pro_id"),
                    rt.getString("pro_name"),
                    rt.getString("pro_total"),
                    rt.getString("uni_name"),
                    rt.getString("pro_price"),
                    rt.getString("pro_import_date"))
            );
            total_mony += rt.getLong("pro_total") * rt.getLong("pro_price");
        }

        col_product.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("TOTAL"));
        col_unit.setCellValueFactory(new PropertyValueFactory<>("UNIT"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("PRICE"));
        col_import_date.setCellValueFactory(new PropertyValueFactory<>("IMPORT_DATE"));
        table.setItems(product);
        txt_total_mony.setText(total_mony + "");

    }

    //2
    private void ManageReminder() {

        try {

            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("select rem_id from SellerReminder where FK_seller_id = " + Seller_ID + ";");
            rt.next();
            if (rt.getRow() == 1) {
                R_ID = rt.getInt("rem_id");
            } else {
                PreparedStatement pre = Database.getInstance().prepareStatement("INSERT INTO SellerReminder(rem_id,reminder,FK_seller_id)VALUES (null,?,?);");
                pre.setDouble(1, total_mony);
                pre.setInt(2, Seller_ID);
                pre.execute();
            }
            if (R_ID >= 1) {
                st = Database.getInstance().createStatement();
                rt = st.executeQuery("SELECT reminder FROM SellerReminder WHERE rem_id=" + R_ID + ";");

                rt.next();
                reminder = rt.getDouble("reminder");
                txt_reminder.setText(reminder + "");
            }
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    //3
    private void updateReminder() {

        try {
            double pay = Double.parseDouble(txt_pay.getText());
            if (pay <= 0 || reminder == 0) {
                Helper.masseg("وردی اشتباه است با باقیمانده صفر است ", Alert.AlertType.INFORMATION);
            } else {
                reminder -= pay;
                String date = txt_pay_date.getText();
                Statement st = Database.getInstance().createStatement();
                int i = st.executeUpdate("UPDATE SellerReminder SET reminder=" + reminder + ", rem_pay_date = '" + date + "' WHERE rem_id = " + R_ID + ";");
                if (i == 1) {
                    Helper.masseg("بروز رسانی صورت گرفت !", Alert.AlertType.INFORMATION);
                } else {
                    Helper.masseg("مضکل وجود دارد !", Alert.AlertType.INFORMATION);
                }
            }
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void btn_ok_onAction(ActionEvent event) {

        updateReminder();
        ManageReminder();
    }

    @FXML
    void comb_seller_onAction(ActionEvent event) {
        try {

            String seler_name = comb_seller.getSelectionModel().getSelectedItem();
            RefreshTable(seler_name);
            ManageReminder();
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    public void btn_close_onAction(ActionEvent actionEvent) {
        Helper.pay_mony_seller.close();
    }

    public void MouseDraggerd(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }


    public void MousePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }
}
