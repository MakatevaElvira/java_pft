package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

public class DbConnectionTest {
  @Test
  public void testDbConnection(){
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/mantis?user=root&password=&serverTimezone=UTC");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("id, username, email, password from mantis_user_table");
      Users users = new Users();
      while (rs.next()) {
        users.add(new User().withId(rs.getInt("id")).withUsername(rs.getString("username"))
                .withEmail(rs.getString("email")).withPassword(rs.getString("password")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(users);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
