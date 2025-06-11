package sample;

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

public class AddNewSeller {

    public static String edite_name, edite_address, edite_number, edite_number2, edite_date = null;
    Helper helper = new Helper();
    ObservableList<TableAddNewSellerAndCustomer> seller = FXCollections.observableArrayList();
    @FXML
    private TableView<TableAddNewSellerAndCustomer> table;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_name;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_addres;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_namber;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_namber2;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_rag_date;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField number;
    @FXML
    private JFXTextField number2;
    @FXML
    private JFXTextField rag_date;

    @FXML
    void MoseDraged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void MosePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    void close_onAction(ActionEvent event) {
        Home.AddNewSeller.close();
    }

    @FXML
    void delete_onAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        int id = table.getSelectionModel().getSelectedItem().getID();
        Statement st = Database.getInstance().createStatement();
        st.execute("DELETE  from seller WHERE sel_id = " + id + ";");
        refreshTable();
    }

    @FXML
    void edite_onAction(ActionEvent event) {
        try {
            edite_name = table.getSelectionModel().getSelectedItem().getNAME();
            edite_address = table.getSelectionModel().getSelectedItem().getADDRESS();
            edite_number = table.getSelectionModel().getSelectedItem().getNUMBER();
            edite_number2 = table.getSelectionModel().getSelectedItem().getNUMBER2();
            edite_date = table.getSelectionModel().getSelectedItem().getRAG_DATE();
            helper.edte_seller();
        } catch (Exception e) {
            Helper.masseg("یک مورد را انتخاب کنید", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void ok_onAction(ActionEvent event) {
        String n, ad, nu, nu2, date = null;
        try {

            n = name.getText();
            ad = address.getText();
            nu = number.getText();
            nu2 = number2.getText();
            date = rag_date.getText();
            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("SELECT * FROM Seller WHERE sel_name='" + n + "'AND sel_address='" + ad + "'AND sel_number='" + nu + "'AND sel_number2='" + nu2 + "'AND sel_rag_date='" + date + "';");
            rt.next();
            if (rt.getRow() == 1) {
                Helper.masseg("فروشنده مورد نظر موجود است", Alert.AlertType.INFORMATION);
            } else {
                PreparedStatement prt = Database.getInstance().prepareStatement("INSERT INTO Seller(sel_id,sel_name,sel_address,sel_number,sel_number2,sel_rag_date)VALUES(null,?,?,?,?,?)");
                prt.setString(1, n);
                prt.setString(2, ad);
                prt.setString(3, nu);
                prt.setString(4, nu2);
                prt.setString(5, date);
                prt.execute();
            }
            refreshTable();
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    public void refreshTable() {
        seller.clear();
        try {
            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("SELECT * FROM seller ORDER by sel_id DESC ;");

            while (rt.next()) {
                seller.add(new TableAddNewSellerAndCustomer(rt.getInt("sel_id"), rt.getString("sel_name"), rt.getString("sel_address"), rt.getString("sel_number"), rt.getString("sel_number2"), rt.getString("sel_rag_date")));
            }
            col_name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            col_addres.setCellValueFactory(new PropertyValueFactory<>("ADDRESS"));
            col_namber.setCellValueFactory(new PropertyValueFactory<>("NUMBER"));
            col_namber2.setCellValueFactory(new PropertyValueFactory<>("NUMBER2"));
            col_rag_date.setCellValueFactory(new PropertyValueFactory<>("RAG_DATE"));
            table.setItems(seller);
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    void initialize() {
        refreshTable();
    }

    @FXML
    public void MouseEnterd(MouseEvent event) {
        refreshTable();
    }
}