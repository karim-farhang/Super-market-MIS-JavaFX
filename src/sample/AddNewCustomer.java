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

public class AddNewCustomer {
    public static String name, address, number, number2, rag_date = null;
    Helper helper = new Helper();
    ObservableList<TableAddNewSellerAndCustomer> table_item = FXCollections.observableArrayList();
    @FXML
    private TableView<TableAddNewSellerAndCustomer> table;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_name;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_address;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_number;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_number2;
    @FXML
    private TableColumn<TableAddNewSellerAndCustomer, String> col_rag_date;
    @FXML
    private JFXTextField txt_name;
    @FXML
    private JFXTextField txt_address;
    @FXML
    private JFXTextField txt_number;
    @FXML
    private JFXTextField txt_number2;
    @FXML
    private JFXTextField txt_rage_date;

    @FXML
    void Cancel(ActionEvent event) {
        txt_name.setText(null);
        txt_address.setText(null);
        txt_number.setText(null);
        txt_number2.setText(null);
        txt_rage_date.setText(null);
    }

    @FXML
    void Close(ActionEvent event) {
        Home.AddNewCustomer.close();
    }

    @FXML
    void Delete(ActionEvent event) {
        try {
            int cus_id = table.getSelectionModel().getSelectedItem().getID();
            Statement st = Database.getInstance().createStatement();
            st.execute("DELETE FROM Customer WHERE cus_id=" + cus_id + ";");
            RefrshTable();
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            Helper.masseg("ار چدول یک مورد حذفی را انتخاب نکرده اید", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void Edite(ActionEvent event) {
        try {

            name = table.getSelectionModel().getSelectedItem().getNAME();
            address = table.getSelectionModel().getSelectedItem().getADDRESS();
            number = table.getSelectionModel().getSelectedItem().getNUMBER();
            number2 = table.getSelectionModel().getSelectedItem().getNUMBER2();
            rag_date = table.getSelectionModel().getSelectedItem().getRAG_DATE();

            helper.Edate_Customer();
        } catch (Exception e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void OK(ActionEvent event) {

        try {
            String na = txt_name.getText();
            String ad = txt_address.getText();
            String nu = txt_number.getText();
            String num = txt_number2.getText();
            String rag = txt_rage_date.getText();

            if (na.equals("")) {
                Helper.masseg(" ثبت نشود محل نام خالی میباشد !", Alert.AlertType.INFORMATION);
            } else {
                Statement st = Database.getInstance().createStatement();
                ResultSet rt = st.executeQuery("SELECT * FROM Customer WHERE cus_name='" + na + "' AND  cus_address = '" + ad + "' AND cus_number='" + num + "';");
                rt.next();
                if (rt.getRow() == 1) {
                    Helper.masseg("مشتری مورد نظر در لیست موجود است ", Alert.AlertType.INFORMATION);
                } else {
                    PreparedStatement prt = Database.getInstance().prepareStatement("INSERT INTO Customer(cus_id,cus_name,cus_address,cus_number,cus_number2,cus_rag_date) values(null,?,?,?,?,?);");
                    prt.setString(1, na);
                    prt.setString(2, ad);
                    prt.setString(3, nu);
                    prt.setString(4, num);
                    prt.setString(5, rag);
                    prt.execute();
                }
            }
            RefrshTable();
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    private void RefrshTable() {
        table_item.clear();
        try {
            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("select * from Customer;");
            while (rt.next()) {
                table_item.add(new TableAddNewSellerAndCustomer(
                        rt.getInt("cus_id"),
                        rt.getString("cus_name"),
                        rt.getString("cus_address"),
                        rt.getString("cus_number"),
                        rt.getString("cus_number2"),
                        rt.getString("cus_rag_date"))
                );
            }
            col_name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            col_address.setCellValueFactory(new PropertyValueFactory<>("ADDRESS"));
            col_number.setCellValueFactory(new PropertyValueFactory<>("NUMBER"));
            col_number2.setCellValueFactory(new PropertyValueFactory<>("NUMBER2"));
            col_rag_date.setCellValueFactory(new PropertyValueFactory<>("RAG_DATE"));
            table.setItems(table_item);

        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void MoseDragged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void MouseReleased(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    void initialize() {
        RefrshTable();
    }

    @FXML
    public void MouseEntared(MouseEvent event) {
        RefrshTable();
    }
}

