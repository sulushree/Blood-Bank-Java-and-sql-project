package MyPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class login extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          String name = request.getParameter("uname");
          
           String bhu = request.getParameter("bhu");
           
           String pass = request.getParameter("pass");
           
           MyDb db = new MyDb();
           Connection con =db.getCon();
           Statement stmt = con.createStatement();
           out.println("hiii");
          if(bhu.equals("user")) 
          {
           ResultSet rs = stmt.executeQuery("select uid,email,pass from uregister where email = '"+name+"' and pass = '"+pass+"'"); 
           rs.next();
           String n = rs.getString("uid");
           HttpSession session=request.getSession();  
           session.setAttribute("uname",n);
          String redirectedPage = "/parentPage";
          response.sendRedirect("welcome.jsp");
          }
        
           if(bhu.equals("b")){
           ResultSet rs1 = stmt.executeQuery("select bid,email,password from bregister where email = '"+name+"' and password = '"+pass+"'"); 
           rs1.next();
            
          response.sendRedirect("welcome.jsp");
        }
       
             if(bhu.equals("h")){
           ResultSet rs2 = stmt.executeQuery("select hid,email,pass from hregister where email = '"+name+"' and pass = '"+pass+"'"); 
           rs2.next();
            
          response.sendRedirect("welcome.jsp");
        } 
            
           
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
