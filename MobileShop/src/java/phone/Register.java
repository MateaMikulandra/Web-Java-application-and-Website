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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mata
 */
public class Register extends HttpServlet {

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

    public static void sendQuery(String username, String password) {

        try {
            PreparedStatement upit = conn.prepareStatement("insert into users (username,password)values(?,?)");
            upit.setString(1, username);
            upit.setString(2, password);
            upit.executeUpdate();
            conn.close();
            System.out.println("upis je uspeo");
        } catch (SQLException e) {
            System.out.println("Upis nije uspeo");
            System.out.println("razlog je: " + e.getMessage());

        }

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
                    sendQuery(username, password);
                    out.print("Registracija je uspela!!");
                }
            } catch (SQLException ex) {
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
