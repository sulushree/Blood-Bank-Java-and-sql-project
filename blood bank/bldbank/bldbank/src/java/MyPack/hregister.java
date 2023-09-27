
package MyPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class hregister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           String name = request.getParameter("name");
           String date = request.getParameter("date");
           String bld = request.getParameter("bld");
           String add = request.getParameter("add");
           String pass = request.getParameter("pass");
           String email = request.getParameter("email");
           
           MyDb db = new MyDb();
           Connection con =db.getCon();
           Statement stmt = con.createStatement();
           
        stmt.executeUpdate("insert into hregister(hname,trdate,bld,address,pass,email)values('"+name+"','"+date+"','"+bld+"','"+add+"','"+pass+"','"+email+"') ");
           
           out.println("register sucess");
           String redirectedPage = "/parentPage";
          response.sendRedirect("registrationsuccess.jsp");
           
           
        } catch (SQLException ex) {
            Logger.getLogger(hregister.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
