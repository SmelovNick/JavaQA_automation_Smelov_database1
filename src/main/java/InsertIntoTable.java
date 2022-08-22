import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoTable {
    public static void main(String[] args) {
        System.out.println("Connecting to database qa_learn...");
        try(Connection connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD);
            Statement statement = connection.createStatement()){
            String sql = "insert into Employees values (100, 18, 'Roman', 'Orlov');";
            statement.executeUpdate(sql);
            System.out.println("Data inserted to table successfully...");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
