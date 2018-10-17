package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
        getServletContext().getRequestDispatcher("/jspfiles/signUp.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserForm userForm = UserForm.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
        PrintWriter printWriter = response.getWriter();
        if (usersService.signUp(userForm)) {
            /*printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('Email and Password is correct! Thank you')");
            printWriter.println("location='/signUp'");
            printWriter.println("</script>");*/
            response.sendRedirect("/signIn");
        } else {
            /*printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('Email or Password is incorrect!')");
            printWriter.println("location='/signUp'");
            printWriter.println("</script>");*/
            response.sendRedirect("/signUpFailed");
        }
    }
}
