package mondrian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConn {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost/foodmart" +
                        "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC" +
                        "&user=foodmart&password=foodmart");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from store");
        while (resultSet.next()) {
            int id = resultSet.getInt("store_id");
            String name = resultSet.getString("store_name");
            System.out.println("#" + id + " " + name);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
