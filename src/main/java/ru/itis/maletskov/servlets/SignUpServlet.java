package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
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
        //usersService = Contexts.primitive().getComponent(UsersService.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis.maletskov/context.xml");
        usersService = context.getBean(UsersService.class);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getServletContext().getRequestDispatcher("/WEB-INF/ftl/signUp.ftl").forward(request, response);
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
            resp.sendRedirect(req.getServletContext().getContextPath() + "/signUp");
        }
    }
}
