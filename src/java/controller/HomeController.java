/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDBContext;
import dal.GroupFavoriteDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Category;
import model.GroupFavorite;
import model.Product;

public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //  page limit 6
        final int PAGE_SIZE = 6;

        // get all categories
        List<Category> listCategories = new CategoryDBContext().getAllCategories();
        request.setAttribute("listCategories", listCategories);

        // default page = 1
        int page = 1;
        
        String pageStr = request.getParameter("page");
        
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        // page size < 1 -> page size = 1
        if (page < 1) {
            page = 1;
        }

        
        ProductDBContext productDAO = new ProductDBContext();
        // total page product
        int totalProducts = productDAO.getTotalProducts();
        
        int totalPage = totalProducts / PAGE_SIZE;
        
        if (totalProducts % PAGE_SIZE != 0) {
            totalPage += 1;
        }
        
        if (page > totalPage) {
            page = totalPage;
        }

        // 
        List<Product> listProducts = productDAO.getProductsWithPagging(page, PAGE_SIZE);
        
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listProducts", listProducts);
        
        request.getSession().setAttribute("urlHistory", "home");

//        if (request.getSession().getAttribute("acc") != null) {
//            Account account = (Account)request.getSession().getAttribute("acc") ;
//            System.out.println(account.getUid());
//            ArrayList<GroupFavorite> listGroupFavorite = new GroupFavoriteDBContext().getAllByIdUser(account.getUid());
//             request.setAttribute("listGroupFavorite", listGroupFavorite);
//        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
