package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("/profile");
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String passwordFirst = req.getParameter("passwordFirst");
        String passwordSecond = req.getParameter("passwordSecond");
        UserForm userForm = UserForm.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .passwordFirst(passwordFirst)
                .passwordSecond(passwordSecond)
                .build();
        if (usersService.signUp(userForm)) {
            resp.sendRedirect("/signUp");
        }
    }
}
