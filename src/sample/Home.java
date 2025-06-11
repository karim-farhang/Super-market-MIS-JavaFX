package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Home {

    @FXML
    private AnchorPane background;

    public static Stage Import, Setting, myReminder, sell, About, Fincel, CustomerReminder, AddNewSeller, AddNewCustomer = null;
    public static ArrayList<String> customers_name;
    public static ArrayList<String> sellers_name;
    public static ArrayList<String> units_name;
    public static ArrayList<String> catagorys_name;

    @FXML
    void CustomerReminder(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerReminder.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        CustomerReminder = stage;
        stage.show();
    }

    @FXML
    void Fincel(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Fininceal.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Fincel = stage;
        stage.show();
    }

    @FXML
    void Import(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ImportProduct.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Import = stage;
        stage.show();
    }

    @FXML
    void Setting(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Setting = stage;
        stage.show();
    }

    @FXML
    void myReminder(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MyReminder.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        myReminder = stage;
        stage.show();
    }

    @FXML
    void sell(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SellProduct.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        sell = stage;
        stage.show();
    }

    public void About(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        About = stage;
        stage.show();
    }

    @FXML
    void initialize() {
        customers_name = new ArrayList<>();
        catagorys_name = new ArrayList<>();
        sellers_name = new ArrayList<>();
        units_name = new ArrayList<>();

    }

    @FXML
    public void addSeller(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddNewSeller.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        AddNewSeller = stage;
        stage.show();
    }

    @FXML
    public void AddCustomer(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddNewCustomer.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        AddNewCustomer = stage;
        stage.show();
    }

    @FXML
    public void Exit(ActionEvent actionEvent) {
        Login.hoom.close();
        Import.close();
        Setting.close();
        myReminder.close();
        sell.close();
        About.close();
        Fincel.close();
        CustomerReminder.close();
        AddNewSeller.close();
        AddNewCustomer.close();
    }
}
