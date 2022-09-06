package operations;

import service.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoTableTransaction {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD);
        System.out.println("Connecting to database qa_learn...");
        try
            {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            String sql = "insert into Employees values (202, 18, 'Roman', 'Orlov');";
            statement.executeUpdate(sql);
            sql = "insert into Employees values (202, 18, 'Roman', 'Orlov');";
            statement.executeUpdate(sql);

            connection.commit();
            System.out.println("Data inserted to table successfully...");
        }
        catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
        }
    }
}
