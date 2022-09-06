package operations;

import service.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTable {
    public static void main(String[] args) {
        System.out.println("Connecting to database qa_learn...");
        try(Connection connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD);
            Statement statement = connection.createStatement()){
            String sql = "drop table Employees;";
            statement.executeUpdate(sql);
            System.out.println("Table dropped successfully...");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
