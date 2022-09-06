package Db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.User;
import service.Config;

public class InsertUsers {
    static List<String> userLogins = List.of(
        "standard_user",
        "locked_out_user",
        "problem_user",
        "perfomance_glitch_user");
    static String password = "secret_sauce";

    static int getCurrentId(Statement statement) throws SQLException {
        String sql = "select * from Users order by id desc;";
        ResultSet rs = statement.executeQuery(sql);
        return rs.next() ? rs.getInt("id")+1 : 1;
    }

    static User getUserByLogin(String user, Statement statement) throws SQLException{
        String sql = String.format("select * from Users where username = '%s'", user);
        ResultSet rs = statement.executeQuery(sql);
        if(!rs.next()) return null;
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"));
    }
    private static void createNewUser(User user, Statement statement) throws SQLException {
        String sql = String.format("insert into Users values (%d, '%s', '%s');",
                user.getId(),
                user.getUsername(),
                user.getPassword());
        statement.executeUpdate(sql);
    }

    public static synchronized List<User> execute() throws SQLException {
        var list = new ArrayList<User>();
        System.out.println("Connecting to database qa_learn...");
        Connection connection = DriverManager.getConnection(Config.SAUCE_DB_URL, Config.USER, Config.PASSWORD);
        try(Statement statement = connection.createStatement())
        {
            connection.setAutoCommit(false);
            int currentId = getCurrentId(statement);

            for(var user: userLogins){
                User existingUser = getUserByLogin(user, statement);
                if(existingUser==null){
                    existingUser = new User(currentId, user, password);
                    createNewUser(existingUser, statement);
                    currentId++;
                }
                list.add(existingUser);
            }

            connection.commit();
            System.out.println("Data inserted to table successfully...");
        }
        catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
        }
        return list;
    }
}
