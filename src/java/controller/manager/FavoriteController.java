/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.GroupFavoriteDBContext;
import dal.OrderDBcontext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author buiph
 */
@WebServlet(name = "FavoriteController", urlPatterns = {"/FavoriteController"})
public class FavoriteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("acc") != null) {
            int idProduct = Integer.parseInt(request.getParameter("idProduct"));
            int idUser = Integer.parseInt(request.getParameter("idUser"));
            int fava = Integer.parseInt(request.getParameter("fava"));
            
            if (fava == 0) {
                new GroupFavoriteDBContext().delete(idUser, idProduct);
            }else{
                new GroupFavoriteDBContext().add(idUser, idProduct);
            }
            response.sendRedirect("home");
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
