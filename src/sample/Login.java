package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    public static int use_id;
    int trying = 0;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private PasswordField txt_password;

    public  static  Stage hoom;
    @FXML
    void ok_on_action(ActionEvent event) {

        try {
            String name = txt_username.getText();
            String password = txt_password.getText();

            Statement st = Database.getInstance().createStatement();
            ResultSet rt = st.executeQuery("SELECT use_id,use_name,use_password FROM users WHERE use_name = '" + name + "' AND use_password = '" + password + "';");
            rt.next();
            if (rt.getRow() != 0) {
                Run.login.close();
                use_id = rt.getInt("use_id");
                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
                stage.setScene(scene);
                stage.setMaximized(true);
                hoom = stage;
                stage.show();
            } else {
                trying++;
                Helper.masseg("اسم ویا گذرواژه نان اشتباه است ! ", Alert.AlertType.WARNING);
            }
            if (trying == 4) {
                Helper.masseg("شما بیشتر از 3 بار کوشش نموده اید .", Alert.AlertType.ERROR);
                Run.login.close();
            }
        } catch (SQLException e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            Helper.masseg(e.getMessage(), Alert.AlertType.INFORMATION);
        }

    }

    public void MouseDraged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    public void MousePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    public void Close(ActionEvent actionEvent) {
        Run.login.close();
    }
}

