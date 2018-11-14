package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {

    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("uid")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            } else if (cookie.getName().equals("uid2")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
