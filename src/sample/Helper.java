package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Helper {

    //about seting
    public static String tableName;
    public static String find;
    public static String name;
    public static Stage add;
    public static Stage edite;
    public static Stage edite_seller;
    public static Stage edite_import_product;
    public static Stage pay_mony_seller;
    public static Stage edite_customer;
    private static double x = 0;
    private static double y = 0;
    //about open same sammler frame
    private Stage stage;
    private Parent root;
    private Scene scene;

    public static void masseg(String msg, Alert.AlertType e) {
        Alert alert = new Alert(e);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void On_mouse_Dragerd(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage1 = (Stage) node.getScene().getWindow();
        stage1.setX(event.getScreenX() - x);
        stage1.setY(event.getScreenY() - y);
    }

    public static void On_mouse_pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    public void add(String qury) throws Exception {
        Helper.tableName = qury;
        root = FXMLLoader.load(getClass().getResource("AddUnitsAndCatagorySettings.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        add = stage;
        stage.showAndWait();
    }

    public void edite(String qury) throws Exception {
        Helper.tableName = qury;
        root = FXMLLoader.load(getClass().getResource("EditeUnitsAndCatagorySettings.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        edite = stage;
        stage.showAndWait();
    }

    public void edte_seller() throws Exception {
        root = FXMLLoader.load(getClass().getResource("EditeSeller.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        edite_seller = stage;
        stage.showAndWait();
    }

    public void edite_import_product() throws Exception {
        root = FXMLLoader.load(getClass().getResource("EdteImportProduct.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        edite_import_product = stage;
        stage.showAndWait();
    }

    public void pay_mony_seller() throws Exception {
        root = FXMLLoader.load(getClass().getResource("PayMonySeller.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        pay_mony_seller = stage;
        stage.showAndWait();
    }

    public void Edate_Customer() throws Exception {
        root = FXMLLoader.load(getClass().getResource("EditeCustomer.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        edite_customer = stage;
        stage.showAndWait();
    }
}
