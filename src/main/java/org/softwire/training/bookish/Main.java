package org.softwire.training.bookish;

import com.mysql.jdbc.Statement;
import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "bookish";
        String user = "root";
        String password = "pickadedev";
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbcMethod(connectionString);
        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        Connection connection = DriverManager.getConnection(connectionString);

        String query = "INSERT INTO books VALUES (1, 'book title', 'book author', '123213', 1)";
        try (Statement statement = (Statement) connection.createStatement()) {
            Boolean res = statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        }

        query = "SELECT * FROM books";
        try (Statement statement = (Statement) connection.createStatement()) {
            ResultSet res = statement.executeQuery(query);

            while (res.next()) {
                System.out.println(res.getString("book_id"));
                System.out.println(res.getString("title"));
                System.out.println(res.getString("author"));
                System.out.println(res.getString("isbn"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        Jdbi jdbi = Jdbi.create(connectionString);

        List<Book> books =  jdbi.withHandle(handle -> {
            handle.execute("INSERT INTO books VALUES (2, 'test', 'me', 'regreger', 1)");

            return handle.createQuery("SELECT * FROM books")
                    .mapToBean(Book.class)
                    .list();
        });
    }
}
