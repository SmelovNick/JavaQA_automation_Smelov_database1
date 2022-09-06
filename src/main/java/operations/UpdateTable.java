package operations;

import service.Config;

import java.sql.*;

public class UpdateTable {
    public static void main(String[] args) {
        System.out.println("Connecting to database qa_learn...");
        try(Connection connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD);
            Statement statement = connection.createStatement()){
            String sql = "update Employees set age = 33 where id = 100";
            statement.executeUpdate(sql);
            System.out.println("Table was updated");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
