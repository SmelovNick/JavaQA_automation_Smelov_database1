import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SelectDB {
    public static void main(String[] args) {
        System.out.println("Connecting to database qa_learn...");
        try(Connection connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD)
        ){
            System.out.println("Connected successfully");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
