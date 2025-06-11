package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImportProduct {

    public static String nameT, catagoryT, totalT, unitT, priceT, i_dateT, e_dateT, selerT = null;
    public static ArrayList<String> units = new ArrayList<>();
    public static ArrayList<String> type = new ArrayList<>();
    public static ArrayList<String> seller = new ArrayList<>();
    Helper helper = new Helper();
    ObservableList<TableUpdateProduct> import_product = FXCollections.observableArrayList();
    @FXML
    private TableView<TableUpdateProduct> table;
    @FXML
    private TableColumn<TableUpdateProduct, String> col_name;
    @FXML
    private TableColumn<TableUpdateProduct, String> col_catagory;
    @FXML
    private TableColumn<TableUpdateProduct, String> col_total;
    @FXML
    private TableColumn<TableUpdateProduct, String> col_unit;
    @FXML
    private TableColumn<TableUpdateProduct, String> col_price;
    @FXML
    private TableColumn<TableUpdateProduct, String> col_import_date;
    @FXML
    private TableColumn<TableUpdateProduct, String> col_expire_date;
    @FXML
    private TableColumn<TableUpdateProduct, String> col_sell_name;
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
    void cancel_onAction(ActionEvent event) {
        txt_name.setText(null);
        comb_catagory.getEditor().setText(null);
        txt_total.setText(null);
        comb_unit.getEditor().setText(null);
        txt_price.setText(null);
        txt_import_date.setText(null);
        txt_expire_date.setText(null);
        comb_sell_name.getEditor().setText(null);
    }

    @FXML
    void delete_onAction(ActionEvent event) {

        try {

            int pro_id = table.getSelectionModel().getSelectedItem().getID();
            Statement st = Database.getInstance().createStatement();
            st.execute("DELETE  from product WHERE pro_id=" + pro_id + ";");
            refreshTable();

        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void ok_onAction(ActionEvent event) {

        try {
            String name = txt_name.getText();
            String catagory = comb_catagory.getSelectionModel().getSelectedItem();
            double total = Double.parseDouble(txt_total.getText());
            String unit = comb_unit.getSelectionModel().getSelectedItem();
            double price = Double.parseDouble(txt_price.getText());
            String import_date = txt_import_date.getText();
            String expire_date = txt_expire_date.getText();
            String seller = comb_sell_name.getSelectionModel().getSelectedItem();

            PreparedStatement preparedStatement = Database.getInstance().prepareStatement("select uni_id from units where uni_name= ? ;");
            preparedStatement.setString(1, unit);
            ResultSet rt = preparedStatement.executeQuery();
            rt.next();
            int uni_id = rt.getInt("uni_id");

            PreparedStatement preparedStatement1 = Database.getInstance().prepareStatement("select cat_id from  catagory where cat_name= ?;");
            preparedStatement1.setString(1, catagory);
            rt = preparedStatement1.executeQuery();
            rt.next();
            int cat_id = rt.getInt("cat_id");

            PreparedStatement preparedStatement2 = Database.getInstance().prepareStatement("SELECT sel_id from seller where sel_name = ? ;");
            preparedStatement2.setString(1, seller);
            rt = preparedStatement2.executeQuery();
            rt.next();
            int sel_id = rt.getInt("sel_id");

            PreparedStatement pst = Database.getInstance().prepareStatement(
                    "INSERT INTO product(pro_id," +
                            "pro_name," +
                            " FK_catgory_id ," +
                            "pro_total," +
                            "FK_unit_id," +
                            "pro_price," +
                            "pro_import_date," +
                            "pro_expire_date," +
                            "FK_seller_id)" +
                            " VALUES (null,?,?,?,?,?,?,?,?);");
            pst.setString(1, name);
            pst.setInt(2, cat_id);
            pst.setDouble(3, total);
            pst.setInt(4, uni_id);
            pst.setDouble(5, price);
            pst.setString(6, import_date);
            pst.setString(7, expire_date);
            pst.setInt(8, sel_id);
            pst.execute();

            refreshTable();

        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void edite_JFXButton_onAction(ActionEvent actionEvent) {

        try {
            nameT = table.getSelectionModel().getSelectedItem().getNAME();
            catagoryT = table.getSelectionModel().getSelectedItem().getCATAGORY();
            totalT = table.getSelectionModel().getSelectedItem().getTOTAL();
            unitT = table.getSelectionModel().getSelectedItem().getUNIT();
            priceT = table.getSelectionModel().getSelectedItem().getPRICE();
            i_dateT = table.getSelectionModel().getSelectedItem().getIMPORT_DATE();
            e_dateT = table.getSelectionModel().getSelectedItem().getEXPIRE_DATE();
            selerT = table.getSelectionModel().getSelectedItem().getSELLER_NAME();
            helper.edite_import_product();

        } catch (RuntimeException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }

    }

    public void pay_onAction(ActionEvent actionEvent) throws Exception {
        helper.pay_mony_seller();
    }

    @FXML
    void initialize() {

        Statement st = null;
        ResultSet rt = null;

        type.clear();
        units.clear();
        seller.clear();

        try {
            st = Database.getInstance().createStatement();
            rt = st.executeQuery("SELECT uni_name from units;");
            while (rt.next()) {
                units.add(rt.getString("uni_name"));
            }

            st = Database.getInstance().createStatement();
            rt = st.executeQuery("SELECT cat_name from catagory;");
            while (rt.next()) {
                type.add(rt.getString("cat_name"));
            }

            st = Database.getInstance().createStatement();
            rt = st.executeQuery("SELECT sel_name from seller;");
            while (rt.next()) {
                seller.add(rt.getString("sel_name"));
            }

            comb_catagory.getItems().setAll(type);
            comb_unit.getItems().setAll(units);
            comb_sell_name.getItems().setAll(seller);
            refreshTable();

        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    private void refreshTable() {
        import_product.clear();
        Statement st = null;
        ResultSet rt = null;

        try {
            st = Database.getInstance().createStatement();
            rt = st.executeQuery("SELECT * FROM Product_update_view;");

            while (rt.next()) {
                import_product.add(new TableUpdateProduct(rt.getInt("pro_id"),
                        rt.getString("pro_name"), rt.getString("cat_name"),
                        rt.getString("pro_total"), rt.getString("uni_name"),
                        rt.getString("pro_price"), rt.getString("pro_import_date"),
                        rt.getString("pro_expire_date"), rt.getString("sel_name")));
            }

            col_name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            col_catagory.setCellValueFactory(new PropertyValueFactory<>("CATAGORY"));
            col_total.setCellValueFactory(new PropertyValueFactory<>("TOTAL"));
            col_unit.setCellValueFactory(new PropertyValueFactory<>("UNIT"));
            col_price.setCellValueFactory(new PropertyValueFactory<>("PRICE"));
            col_import_date.setCellValueFactory(new PropertyValueFactory<>("IMPORT_DATE"));
            col_expire_date.setCellValueFactory(new PropertyValueFactory<>("EXPIRE_DATE"));
            col_sell_name.setCellValueFactory(new PropertyValueFactory<>("SELLER_NAME"));

            table.setItems(import_product);

        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void Close(ActionEvent actionEvent) {
        Home.Import.close();
    }

    @FXML
    public void MouseEntered(MouseEvent event) {
        refreshTable();
    }
}
