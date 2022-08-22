import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteFromTable {
    public static void main(String[] args) {
        System.out.println("Connecting to database qa_learn...");
        try(Connection connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD);
            Statement statement = connection.createStatement()){
            String sql = "delete from Employees where id = 101";
            statement.executeUpdate(sql);
            System.out.println("Record was deleted");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
