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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mata
 */
public class MobileStore extends HttpServlet {

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

    @Override
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

        try {
            if (connect()) {

                String sql = "SELECT * FROM phones WHERE quantity1 > 0";
                Statement upit = conn.createStatement();
                ResultSet rs = upit.executeQuery(sql);
                if (!rs.next()) {
                    out.println("<h3>No phones found!!</h3>");
                } else {
                    out.println("<html style='height: 100%;background-color: lightviolet'>");
                    out.println("<form method='get' action='Order'>");
                     out.println("<center><img src='images/logo.jpg' style='height: 70px;'></center>");
                      out.println("<center><h1 style='font-style: Cabin;\n" +"  text-align: center;\n" +"  color: rgb(91, 50, 180);\n" +"  font-size: 40px;\n" +"  text-shadow: 2px 2px 8px #8A2BE2;'>Izaberi svoju omiljenu boju</h1></center>");                  
                    
                    do {

                        String id = rs.getString("phone_id");
                        
                        out.println("");
                        out.println("<center><br><div style='color: 5000FF; background-color: rgb(233,219,232); width:600px; height:630px; font-size: 20px; border: 1px dashed rgb(91, 50, 180); border-radius: 25%'>");                 
                        out.println("<p style='font-size: 25px;'><b>" + rs.getString("name1") + "</b></p>");
                        out.println("<p>Na stanju: " + rs.getString("quantity1") + " kom</p>");
                        out.println("<p>Cena: " + rs.getString("price") + " e</p>");
                        String source = "images/"+rs.getString("name1")+".png";
                        String slika = "<p><img src='"+source+"'></p>";
                        out.println(slika);
                        out.println("<p><input type='text' size='3' value='1' name='kol" + id + "' /></p>");
                        out.println("<p>" + rs.getString("gb") + " gb</p>");
                        String source1 = "images/"+rs.getString("price")+ ".png";
                        String slika1 ="<p><img src='"+source1+"'></p>";
                        out.println(slika1);
                        out.println("<p style='font-size: 30px;'>&nbsp<b style='background-color:lightblue; border: 0.5px solid blue; border-radius: 15%;'><input type='checkbox' name='id' value='" + id + "' />Buy&nbsp</b></p>");
                        out.println("</div></center><br>");
                       
                    } while (rs.next());
                    out.println("<center><h2 style='color: 5000FF;'>Unesite podatke za porudžbinu</h2></center>");
                    out.println("<br>");
                    out.println("<center><table>");
                    out.println("<tr style='color: 5000FF; font-size: 20px;'><td>Unesite ime:</td>");
                    out.println("<td><input type='text' name='name' /></td></tr>");
                    out.println("<tr style='color: 5000FF; font-size: 20px;'><td>Unesite prezime:</td>");
                    out.println("<td><input type='text' name='prezime' /></td></tr>");
                    out.println("<tr style='color: 5000FF; font-size: 20px;'><td>Unesite email:</td>");
                    out.println("<td><input type='text' name='email' /></td></tr>");
                    out.println("<tr style='color: 5000FF; font-size: 20px;'><td>Unesite broj telefona:</td>");
                    out.println("<td><input type='text' name='phone' /></td></tr></table><br />");
                    out.println("<input type='submit' value='PORUČI' />");
                    out.println("<input type='reset' value='IZBRISI' /></form></center>");
                    out.println("</html>");

                }

            }
        } catch (SQLException e) {
            System.out.println("razlog je: " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
