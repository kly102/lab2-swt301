/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import dal.AcountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // sử dụng session để ktra đăng nhập
        if (request.getSession().getAttribute("acc") != null) {
            // nếu đã đăng nhập chuyển về home
            response.sendRedirect("home");
        } else {
            // chưa đăng nhập chuyển về login jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

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
        // sử dụng session để ktra đăng nhập
        if (request.getSession().getAttribute("acc") != null) {
            // chưa đăng nhập chuyển về login jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
        }
        // getParameter dùng để ktra tài khoản
        String user = request.getParameter("Username");
        String pass = request.getParameter("Password");
        AcountDBContext adb = new AcountDBContext();
        // ktra tài khoản login user, pass
        Account a = adb.login(user, pass);
        // tài khoản null -> sai đăng nhập
        if (a == null) {
            request.setAttribute("user", user);
            request.setAttribute("pass", pass);
            request.setAttribute("mess", "Wrong user or pass");
            // chuyển về login jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
            // Active null -> bị ban    
        } else if (a.isActive() == false) {
            request.setAttribute("user", user);
            request.setAttribute("pass", pass);
            request.setAttribute("mess", "Account has been banned");
            // chuyển về login jsp
            request.getRequestDispatcher("login.jsp").forward(request, response);
            // sử dụng session để lưu tk vừa đăng nhập
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            // check Admin 
            if (a.getIsAdmin() == 1) {
                session.setAttribute("role_admin", a.getIsAdmin());
            }
            // session dùng để lưu tgian tkhoan đăng nhập (24h)
            session.setMaxInactiveInterval(60 * 60 * 24);
            // sau khi đăng nhập chuyển lại về home
            response.sendRedirect("home");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
