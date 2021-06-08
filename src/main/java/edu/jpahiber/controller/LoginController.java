package edu.jpahiber.controller;


import edu.jpahiber.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            authenticate(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void authenticate(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String pass = req.getParameter("password");

        if (userService.userVerification(username, pass)){
            resp.sendRedirect("main");
        } else {
//            throw new Exception("Login not successful!");
            out.write("<h2>" + "Username or password is incorrect!" + "</h2>");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.include(req, resp);
        }

        out.close();

    }

}
