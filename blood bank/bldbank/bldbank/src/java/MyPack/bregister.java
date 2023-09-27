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
public class bregister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        String name = request.getParameter("name");
          
           String bld = request.getParameter("bld");
           String add = request.getParameter("add");
           String email = request.getParameter("email");
           String pass = request.getParameter("pass");
           
           MyDb db = new MyDb();
           Connection con =db.getCon();
           Statement stmt = con.createStatement();
           
        stmt.executeUpdate("insert into bregister(bname,bld,address,password,email)values('"+name+"','"+bld+"','"+add+"','"+pass+"','"+email+"') ");
           
           out.println("register sucess");
            String redirectedPage = "/parentPage";
          response.sendRedirect("registrationsuccess.jsp");
           
           
        } catch (SQLException ex) {
            Logger.getLogger(bregister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
