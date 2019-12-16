package com.nmcnpm.nhom16.controller;

import com.nmcnpm.nhom16.entities.Account;
import com.nmcnpm.nhom16.service.AccountService;
import com.nmcnpm.nhom16.service.IAccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class Login extends HttpServlet {
    AccountService accountService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        accountService = new AccountService();
        HttpSession session  = request.getSession();
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        session.setAttribute("user",user);
        boolean checkLogin = accountService.checkLogin(new Account(user,password));
        System.out.println(checkLogin);
        if (checkLogin){
            session.setAttribute("user",user);
            session.setAttribute("password",password);
            response.sendRedirect("HomePage.jsp");
        }else {
            response.sendRedirect("Login.jsp?err=1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
