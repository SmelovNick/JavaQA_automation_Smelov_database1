import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        System.out.println("Connecting to database qa_learn...");
        try(Connection connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD);
            Statement statement = connection.createStatement()){
            String sql = "create table Employees(\n" +
                    "    id int not null,\n" +
                    "    age int not null,\n" +
                    "    firstname varchar(255),\n" +
                    "    lastname varchar(255),\n" +
                    "    primary key (id)\n" +
                    ");";
            statement.executeUpdate(sql);
            System.out.println("Table created successfully...");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
