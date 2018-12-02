package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.context.Contexts;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    private UsersService usersService;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {
        usersService = Contexts.primitive().getComponent(UsersService.class);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        boolean isHavingUid = false;

        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("uid")) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    isHavingUid = true;
                    break;
                }
            }
        }

        //сделай по-другому через удаление аттрибутов
        if (isHavingUid) {
            usersService.logOut((User) req.getSession().getAttribute("user"));
            req.getSession().invalidate();
        } else {
            req.getSession().invalidate();
        }

        req.getRequestDispatcher("/WEB-INF/ftl/signUp.ftl").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
