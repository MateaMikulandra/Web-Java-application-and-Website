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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mata
 */
public class Order extends HttpServlet {

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
        out.println("<html style='height: 100%;background-color: white'>");
        out.println("<div align='center'>");
        out.println("<center><img src='images/logo.jpg' style='height: 70px;'></center>");
        out.println("<h2 style='color: 5000FF; font-size: 35px;'>Potvrda porudžbine</h2>");

        String[] ids = request.getParameterValues("id");
        String name = request.getParameter("name");
        boolean bname = name != null;
        String prezime = request.getParameter("prezime");
        boolean bprezime = prezime != null;
        String email = request.getParameter("email");
        boolean bemail = email != null;
        String phone = request.getParameter("phone");
        boolean bphone = phone != null;
        if (ids == null || ids.length == 0) {
            out.println("<h3>Please Select a mobile phone</h3>");
        } else {
            out.println("<table>");
            out.println("<tr style='color: 5000FF; font-size: 15px;'><td>Ime:</td><td>" + name + "</td></tr>");
            out.println("<tr style='color: 5000FF; font-size: 15px;'><td>Prezime:</td><td>" + prezime + "</td></tr>");
            out.println("<tr style='color: 5000FF; font-size: 15px;'><td>Email:</td><td>" + email + "</td></tr>");
            out.println("<tr style='color: 5000FF; font-size: 15px;'><td>Broj telefona:</td><td>" + phone + "</td></tr></table>");
        }
        try {
            connect();
            String sql;
            Statement upit = conn.createStatement();
            
            
          
            double ukupnacena = 0;
            for (String id : ids) {
                sql = "SELECT * FROM phones WHERE phone_id = " + id;
                ResultSet rs = upit.executeQuery(sql);
                rs.next();
                int qtyAvailable = rs.getInt("quantity1");
                String name1 = rs.getString("name1");
                double price = rs.getDouble("price");
                int gb = rs.getInt("gb");
              
                
                int qtyOrdered = Integer.parseInt(request.getParameter("kol" + id));
                System.out.println(qtyOrdered);
                sql = "UPDATE phones SET quantity1 = quantity1 - " + qtyOrdered + " WHERE phone_id = " + id;
                upit.executeUpdate(sql);
                
                PreparedStatement pupit = conn.prepareStatement("insert into purchases (quantity,name,prezime,email,phone)values(?,?,?,?,?)");
                pupit.setInt(1, qtyOrdered);
                pupit.setString(2, name);
                pupit.setString(3, prezime);
                pupit.setString(4, email);
                pupit.setString(5, phone);
                pupit.executeUpdate();
                out.println("<div>");
                out.println("<strong><p style='color: 5000FF; font-size: 20px;'>" + name1 + "");
                out.println(+ gb + " gb" + "</p></strong></div>");
                out.println("<p style='color: 5000FF; font-size: 15px;'>"+ "Cena: " + price + " e" + "</p>");
                out.println("<p style='color: 5000FF; font-size: 15px;'>"+ "Količina: " + qtyOrdered + " kom" + "</p>");
                String source = "images/"+name1+".png";
                String slika = "<img src='"+source+"' style='width:150px; height: 190px;'>";
                out.println(slika);
                out.println("<br/>");
                
                ukupnacena += price * qtyOrdered;
            }

            out.println("<strong><tr style='color: 5000FF; font-size: 15px;'><td colspan='7' align='right'>Ukupna cena u evrima iznosi : ");
            out.printf("%.2f</td></tr></strong>", ukupnacena);
            out.println("</table>");
            out.println("<h3 style='color: 5000FF; font-size: 15px;'>Poručili ste Samsung S9 mobilni telefon. Hvala sto koristite naše usluge.</h3>");
            out.println("</div>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
