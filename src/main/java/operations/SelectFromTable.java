package operations;

import service.Config;

import java.sql.*;

public class SelectFromTable {
    public static void main(String[] args) {
        System.out.println("Connecting to database qa_learn...");
        try(Connection connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD);
            Statement statement = connection.createStatement()){
            String sql = "select * from Employees";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                System.out.println("{");
                System.out.println("\tid: " + rs.getInt("id"));
                System.out.println("\tage: " + rs.getInt("age"));
                System.out.println("\tfirstname: " + rs.getString("firstname"));
                System.out.println("\tlastname: " + rs.getString("lastname"));
                System.out.println("}");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
