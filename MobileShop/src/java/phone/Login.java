/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mata
 */
public class Login extends HttpServlet {

    static Connection conn;

    public static boolean connect() throws SQLException {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobile", "root", "");
            boolean statusconn = conn.isClosed();
            if (statusconn == false) {
                System.out.println("Konekcija je uspela");
                return true;
            }

        } catch (Exception e) {
            System.out.println("Konekcija nije uspela");
            System.out.println("Razlog je: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void init() throws ServletException {
        try {
            connect();
        } catch (SQLException ex) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean busername = username != null && ((username = username.trim()).length() > 0);
        boolean bpassword = password != null && ((password = password.trim()).length() > 0);

        if (!busername) {
            out.println("<h3>Please Enter Your username!</h3>");
        } else if (!bpassword) {
            out.println("<h3>Please Enter Your password!</h3>");
        } else {

            try {
                if (connect()) {

                    PreparedStatement upit = conn.prepareStatement("SELECT username,password  FROM users WHERE (username=? AND password=?)");
                    upit.setString(1, username);
                    upit.setString(2, password);
                    ResultSet rs = upit.executeQuery();
                    if (!rs.next()) {  
                        out.println("<h3>Wrong username/password!</h3>");
                        out.println("<p><a href='index.html'>Back to Login</a></p>");
                    } else {
                        out.println("<center><img src='images/logo.jpg' style='height: 70px;' align='center'></center>");
                        out.println("<p style='color: A020F0; font-size: 24px;' align='center'><bold>Pozdrav, " + username + "!</bold></p>");
                        out.println("<p style='color: a020f0; font-size: 40px;' align='center'><strong>Poruči svoj Samsung S9</strong></p>");
                        out.println("<img src='images/purple.gif' style='width: 300px; height: 400px;' align='left'>");
                        out.println("<center><a href='mobilestore'><img src='images/buy-icon.jpg' style='width: 200px; height: 200px; margin-right: 300px;'></a></center>");
                    }
                      
                    conn.close();

                }
            } catch (SQLException e) {
                System.out.println("razlog je: " + e.getMessage());
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
