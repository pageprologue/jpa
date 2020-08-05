package study.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JDBCTests {

    @Test
    public void connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:25432/springdata";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection created: " + connection);
        }
    }

    @Test
    public void createTable() throws SQLException {
        String url = "jdbc:postgresql://localhost:25432/springdata";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "CREATE TABLE ACCOUNT (id int, username varchar(255), password varchar(255))";

            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }
        }
    }

    @Test
    public void insertTable() throws SQLException {
        String url = "jdbc:postgresql://localhost:25432/springdata";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO ACCOUNT VALUES (1, 'Cloes', 'password')";

            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }
        }
    }


}