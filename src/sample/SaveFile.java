package sample;

import java.sql.ResultSet;
import java.sql.Statement;

public class SaveFile {


    public static void main(String[] args) throws Exception {

        Statement statement = Database.getInstance().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT use_name from users;");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("use_name"));
        }
    }
}
