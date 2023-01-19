package nu.rolandsson.db;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDatabase {
  private static MysqlDatabase instance;
  private Connection connection;

  private MysqlDatabase() {
    String url = "jdbc:mysql://localhost:3306/awshopping";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, "root", "");
    } catch (SQLException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static MysqlDatabase getInstance() {
    if(instance == null) {
      instance = new MysqlDatabase();
    }

    return instance;
  }

  public Connection getConnection() {
    return connection;
  }
}
