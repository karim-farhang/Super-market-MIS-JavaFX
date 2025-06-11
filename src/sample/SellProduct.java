package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SellProduct {

    public static int LastSell = 0;
    public static Stage printbill;
    ObservableList<TableSellProduct> table_item = FXCollections.observableArrayList();
    ArrayList<String> product_name = new ArrayList<>();
    ArrayList<String> customer_name = new ArrayList<>();
    @FXML
    private TableView<TableSellProduct> table;
    @FXML
    private TableColumn<TableSellProduct, String> col_name;
    @FXML
    private TableColumn<TableSellProduct, String> col_total;
    @FXML
    private TableColumn<TableSellProduct, String> col_unit;
    @FXML
    private TableColumn<TableSellProduct, String> col_price;
    @FXML
    private TableColumn<TableSellProduct, String> col_customar;
    @FXML
    private JFXComboBox<String> comb_product_name;
    @FXML
    private Label total;
    @FXML
    private Label unit;
    @FXML
    private Label price;
    @FXML
    private JFXTextField total_sell;
    @FXML
    private JFXTextField price_sell;
    @FXML
    private JFXComboBox<String> comb_customer;

    @FXML
    void initialize() {
        product_name.clear();
        customer_name.clear();
        try {
            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("SELECT pro_name FROM product;");
            while (rt.next()) {
                product_name.add(rt.getString("pro_name"));
            }
            rt = st.executeQuery("SELECT cus_name from Customer;");
            while (rt.next()) {
                customer_name.add(rt.getString("Cus_name"));
            }
            comb_product_name.getItems().setAll(product_name);
            comb_customer.getItems().setAll(customer_name);

            rt = st.executeQuery("SELECT fac_id from factor ORDER by fac_id DESC ;");
            rt.next();
            if (rt.isFirst()) {
                LastSell = rt.getInt("fac_id");
            }

        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void btn_cncel_onAction(ActionEvent event) {
        comb_product_name.getSelectionModel().clearSelection();
        comb_customer.getSelectionModel().isSelected(-1);
        total.setText("-----");
        unit.setText("-----");
        price.setText("-----");
        total_sell.setText("");
    }

    @FXML
    void btn_ok_onAction(ActionEvent event) {

        try {
            String product = comb_product_name.getSelectionModel().getSelectedItem();
            double TotalInventory = Double.parseDouble(total.getText());
            double TotalSell = Double.parseDouble(total_sell.getText());
            String customer_name = this.comb_customer.getSelectionModel().getSelectedItem();
            double reminderProduct = TotalInventory - TotalSell;

            int pro_id = 0;
            int cus_id = 0;
            int fac_id = 0;

            PreparedStatement pst1 = Database.getInstance().prepareStatement("SELECT pro_id FROM product WHERE pro_name= ?;");
            pst1.setString(1, product);
            ResultSet resultSet = pst1.executeQuery();
            resultSet.next();
            pro_id = resultSet.getInt("pro_id");

            PreparedStatement pst2 = Database.getInstance().prepareStatement("SELECT cus_id FROM customer WHERE cus_name = ? ;");
            pst2.setString(1, customer_name);
            ResultSet resultSet1 = pst2.executeQuery();
            resultSet1.next();
            cus_id = resultSet1.getInt("cus_id");

            PreparedStatement Pst3 = Database.getInstance().prepareStatement(" insert into factor(fac_id,FK_cus_id,FK_pro_id,fac_total)values(null,?,?,?);");
            Pst3.setInt(1, cus_id);
            Pst3.setInt(2, pro_id);
            Pst3.setDouble(3, TotalSell);
            Pst3.execute();

            PreparedStatement pst4 = Database.getInstance().prepareStatement("UPDATE product set pro_total =  ? WHERE pro_name = ? ;");
            pst4.setDouble(1, reminderProduct);
            pst4.setString(2, product);
            pst4.executeUpdate();
            RefreshTable();
            PreparedStatement pst5 = Database.getInstance().prepareStatement("SELECT pro_total FROM product WHERE pro_name = ? ;");
            pst5.setString(1, product);
            ResultSet resultSet2 = pst5.executeQuery();
            resultSet2.next();

            Statement insettoBill = Database.getInstance().createStatement();
            ResultSet rst = insettoBill.executeQuery("SELECT fac_id FROM factor ORDER by fac_id DESC ;");
            rst.next();
            fac_id = rst.getInt("fac_id");

            PreparedStatement pst6 = Database.getInstance().prepareStatement("INSERT into bill(bill_id,fac_id,use_id) values(null,?,?);");
            pst6.setInt(1, fac_id);
            pst6.setInt(2, Login.use_id);
            pst6.execute();
            Helper.masseg("yes ", Alert.AlertType.INFORMATION);
            total.setText(resultSet2.getDouble("pro_total") + "");
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    private void RefreshTable() {

        table_item.clear();
        try {
            PreparedStatement preparedStatement = Database.getInstance().prepareStatement("SELECT * FROM  SellProduct WHERE fac_id > ? ;");
            preparedStatement.setInt(1, LastSell);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                table_item.add(new TableSellProduct(
                        resultSet.getInt("fac_id"),
                        resultSet.getString("pro_name"),
                        resultSet.getFloat("fac_total"),
                        resultSet.getString("uni_name"),
                        resultSet.getFloat("pro_price"),
                        resultSet.getString("cus_Name"))
                );
            }
            col_name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            col_total.setCellValueFactory(new PropertyValueFactory<>("TOTAL"));
            col_unit.setCellValueFactory(new PropertyValueFactory<>("UNIT"));
            col_price.setCellValueFactory(new PropertyValueFactory<>("PRICE"));
            col_customar.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_NAME"));
            table.setItems(table_item);
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void btn_print_factor_onActio(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("PrintBill.fxml"));
            Scene scene = new Scene(parent);
            printbill = new Stage();
            printbill.initModality(Modality.APPLICATION_MODAL);
            printbill.initStyle(StageStyle.TRANSPARENT);
            printbill.setScene(scene);
            printbill.showAndWait();
        } catch (Exception e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void comb_product_name_onAction(ActionEvent event) {

        String P_name = comb_product_name.getSelectionModel().getSelectedItem();
        try {
            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery(
                    "SELECT pro_total,uni_name,pro_price from product,units WHERE units.uni_id = product.FK_unit_id AND pro_name='" + P_name + "';");
            rt.next();
            total.setText(rt.getString("pro_total"));
            unit.setText(rt.getString("uni_name"));
            price.setText(rt.getString("pro_price"));

        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    public void btn_Close_onAction(ActionEvent actionEvent) {
        Home.sell.close();
    }

    @FXML
    void Mouse_Dragged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void Mouse_Pressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    public void MouseEneatrd(MouseEvent event) {
        RefreshTable();
    }

    @FXML
    public void btn_Delete(ActionEvent actionEvent) {
        try {

            int fac_id = table.getSelectionModel().getSelectedItem().getID();
            double back = table.getSelectionModel().getSelectedItem().getTOTAL();
            Helper.masseg(back + "", Alert.AlertType.INFORMATION);
            String name = table.getSelectionModel().getSelectedItem().getNAME();

            PreparedStatement statement = Database.getInstance().prepareStatement("DELETE FROM factor WHERE fac_id = ? ;");
            statement.setInt(1, fac_id);
            statement.execute();

            PreparedStatement preparedStatement = Database.getInstance().prepareStatement("SELECT pro_id,pro_total FROM product WHERE pro_name = ? ;");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int pro_id = resultSet.getInt("pro_id");
            double total = resultSet.getDouble("pro_total");

            back = back + total;
            PreparedStatement statement1 = Database.getInstance().prepareStatement("UPDATE product set pro_total= ? WHERE pro_id = ?;");
            statement1.setDouble(1, back);
            statement1.setInt(2, pro_id);
            statement1.execute();

            PreparedStatement preparedStatement1 = Database.getInstance().prepareStatement("SELECT pro_total FROM product WHERE pro_name = ? ;");
            preparedStatement1.setString(1, name);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            double Remind = resultSet1.getDouble("pro_total");
            this.total.setText(Remind + "");

            PreparedStatement deleteBill = Database.getInstance().prepareStatement("DELETE FROM  bill WHERE fac_id= ? ;");
            deleteBill.setInt(1, fac_id);
            deleteBill.execute();

            RefreshTable();

        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }
}
