import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(Config.SERVER_URL, Config.USER, Config.PASSWORD);
            Statement statement = connection.createStatement()
            ){
            String sql = "CREATE DATABASE qa_learn;";
            statement.executeUpdate(sql);
            System.out.println("Database created successfully");
        }
        catch (SQLException e){
            e.printStackTrace();

        }
    }
}
