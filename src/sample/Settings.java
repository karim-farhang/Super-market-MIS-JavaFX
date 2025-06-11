package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Settings {

    ObservableList<String> catagores = FXCollections.observableArrayList();
    ObservableList<String> units = FXCollections.observableArrayList();
    ObservableList<String> users = FXCollections.observableArrayList();
    Helper helper = new Helper();
    @FXML
    private ListView<String> Catagory_list;
    @FXML
    private ListView<String> Units_list;
    @FXML
    private ListView<String> User_list;

    @FXML
    void catagory_add_onAction(ActionEvent event) throws Exception {
        helper.add("catagory");
        refreshCatagory();
    }

    @FXML
    void catagory_delete_onAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String seleItem = Catagory_list.getSelectionModel().getSelectedItem();
        Statement st = Database.getInstance().createStatement();
        st.execute("DELETE  FROM catagory WHERE cat_name='" + seleItem + "';");
        refreshCatagory();
    }

    public void catagory_edite_onAction(ActionEvent actionEvent) throws Exception {
        Helper.find = Catagory_list.getSelectionModel().getSelectedItem();
        Helper.name = "sel_name";
        helper.edite("catagory");
        refreshCatagory();
    }

    public void Units_add_onAction(ActionEvent actionEvent) throws Exception {
        helper.add("units");
        refreshUnits();
    }

    public void Units_delete_onAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = Units_list.getSelectionModel().getSelectedItem();
        Statement st = Database.getInstance().createStatement();
        st.execute("DELETE FROM units WHERE uni_name='" + name + "';");
        refreshUnits();
    }

    public void Units_edite_onAction(ActionEvent actionEvent) throws Exception {
        Helper.find = Units_list.getSelectionModel().getSelectedItem();
        Helper.name = "uni_name";
        helper.edite("units");
        refreshUnits();
    }

    public void User_delete_onAction(ActionEvent actionEvent) {

    }

    public void User_add_onAction(ActionEvent actionEvent) {

    }

    public void User_edite_onAction(ActionEvent actionEvent) {

    }


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        refreshCatagory();
        refreshUnits();
        refreshUser();
    }

    private void refreshCatagory() throws SQLException, ClassNotFoundException {
        catagores.clear();
        PreparedStatement st = Database.getInstance().prepareStatement("SELECT cat_name FROM catagory;");
        ResultSet rt = st.executeQuery();
        while (rt.next()) {
            catagores.add(rt.getString("cat_name"));
        }
        Catagory_list.setItems(catagores);
    }

    private void refreshUnits() throws SQLException, ClassNotFoundException {
        units.clear();
        PreparedStatement st = Database.getInstance().prepareStatement("SELECT uni_name FROM units;");
        ResultSet rt = st.executeQuery();
        while (rt.next()) {
            units.add(rt.getString("uni_name"));
        }
        Units_list.setItems(units);
    }

    private void refreshUser() throws SQLException, ClassNotFoundException {
        users.clear();
        PreparedStatement preparedStatement = Database.getInstance().prepareStatement("SELECT use_name FROM users;");
        ResultSet rt = preparedStatement.executeQuery();
        while (rt.next()) {
            users.add(rt.getString("use_name"));
        }
        User_list.setItems(users);
    }

    @FXML
    public void MouseDragged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    public void MousePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    public void MouseEntered(MouseEvent event) {
        try {
            refreshCatagory();
            refreshUser();
            refreshUnits();
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (ClassNotFoundException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }
}
