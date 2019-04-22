package ru.itis.maletskov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.models.forms.UserForm;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/updateUserInfo")
public class UpdateUserServlet extends HttpServlet {
    private UsersService usersService;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/ftl/profile.ftl").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String firstPassword = req.getParameter("firstPassword");
        String secondPassword = req.getParameter("secondPassword");
        UserForm userForm = UserForm.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .passwordFirst(firstPassword)
                .passwordSecond(secondPassword)
                .build();
        boolean updated = usersService.updateInfo(userForm, user.getId());
        String json = objectMapper.writeValueAsString(updated);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        if (updated) {
            resp.sendRedirect("/profile");
        }
    }
}