/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.AcountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManagerAccountController", urlPatterns = {"/managerAccount"})
public class ManagerAccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("acc") != null && request.getSession().getAttribute("role_admin") != null) {
            
            AcountDBContext acountDBContext = new AcountDBContext();
            // page
            final int PAGE_SIZE = 10;
            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            if (page < 1) {
                page = 1;
            }
            // lấy số lượng account
            int totalAccount = acountDBContext.getTotalAccount();
            int totalPage = totalAccount / PAGE_SIZE;
            if (totalAccount % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            List<Account> accounts = acountDBContext.getAllAccountByPage(page, PAGE_SIZE);
            request.setAttribute("accounts", accounts);
            request.getRequestDispatcher("ManagerCustomer.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
