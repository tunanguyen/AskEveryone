/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import constant.RequestAttribute;
import constant.RequestParameter;
import constant.ResponseCode;
import constant.ResponseMessage;
import entity.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Validator;

/**
 *
 * @author Apollo
 */
public class RegisterController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String username = request.getParameter(RequestParameter.USERNAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            if (request.getParameter(RequestParameter.SUBMIT) != null
                    && Validator.isValidParameters(username, email, password)) {
                if (UserDAO.getInstance().cheackUsernameExist(username) == true) {
                    request.setAttribute(RequestAttribute.CODE, ResponseCode.INTERNAL_SERVER_ERROR);
                    request.setAttribute(RequestAttribute.MESSAGE, ResponseMessage.REGISTERED_USERNAME);
                } else if (UserDAO.getInstance().cheackEmailExist(email) == true) {
                    request.setAttribute(RequestAttribute.CODE, ResponseCode.INTERNAL_SERVER_ERROR);
                    request.setAttribute(RequestAttribute.MESSAGE, ResponseMessage.REGISTERED_EMAIL);
                } else {
                    User user = new User(username, password, email);
                    if (!UserDAO.getInstance().createAccount(user)) {
                        request.setAttribute(RequestAttribute.CODE, ResponseCode.INTERNAL_SERVER_ERROR);
                        request.setAttribute(RequestAttribute.MESSAGE, ResponseMessage.REGISTER_FAILURE);
                    } else {
                        request.setAttribute(RequestAttribute.CODE, ResponseCode.SUCCESS);
                        request.setAttribute(RequestAttribute.MESSAGE, ResponseMessage.REGISTER_SUCCESSFULLY);
                    }
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
