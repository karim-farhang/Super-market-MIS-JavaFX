package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.sql.Statement;

public class AddUnitsAndCatagorySettings {

    @FXML
    private JFXTextField txt_add;

    @FXML
    void Mose_Drageed(MouseEvent event) {
        Helper.On_mouse_Dragerd(event);
    }

    @FXML
    void Mouse_Pressed(MouseEvent event) {
        Helper.On_mouse_pressed(event);
    }

    public void close_onAction(ActionEvent actionEvent) {
        Helper.add.close();
    }

    public void ok_onAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String name = txt_add.getText();
        Statement st = Database.getInstance().createStatement();
        st.execute("INSERT INTO " + Helper.tableName + " values(null,'" + name + "');");
        txt_add.setText(null);
    }
}
