package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.sql.Statement;

public class EditeUnitsAndCatagorySettings {

    @FXML
    private Label text_first;

    @FXML
    private JFXTextField text_last;
    @FXML
    private JFXButton close;

    @FXML
    private JFXButton riplce;

    @FXML
    void background_onMouseDraged(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void background_onMousePressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    @FXML
    void clost_onAction(ActionEvent event) {
        Helper.edite.close();
    }

    @FXML
    void riplace_onAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String last = text_last.getText();
        String farst = text_first.getText();
        Statement st = Database.getInstance().createStatement();

        st.execute("UPDATE '" + Helper.tableName + "' set '" + Helper.name + "' ='" + last + "' WHERE '" + Helper.name + "' ='" + farst + "';");

        text_first.setText(last);
        text_last.setText(null);
        text_last.setDisable(true);
        riplce.setDisable(true);

    }

    @FXML
    void initialize() {
        text_first.setText(Helper.find);
        text_last.setText(Helper.find);

    }
}
