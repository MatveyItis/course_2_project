package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.forms.AuthForm;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
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
        request.getServletContext().getRequestDispatcher("/WEB-INF/ftl/signUp.ftl").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("e-mail");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("remember");
        LoginForm loginForm = LoginForm.builder()
                .email(email)
                .password(password)
                .build();
        if (usersService.signIn(loginForm)) {
            HttpSession session = req.getSession(true);
            User user = usersService.findByEmail(loginForm.getEmail());
            session.setAttribute("user", user);
            session.setAttribute("authorized", "true");
            if (rememberMe != null && rememberMe.equals("on")) {
                Cookie uid = new Cookie("uid", session.getId());
                uid.setMaxAge(60 * 60 * 240);
                resp.addCookie(uid);
                usersService.auth(AuthForm.builder()
                        .cookieValue(session.getId())
                        .clientId(user.getClientId())
                        .build());
            }
            resp.sendRedirect(req.getServletContext().getContextPath() + "/profile");
        } else {
            resp.sendRedirect(req.getServletContext().getContextPath() + "/signUp");
        }
    }
}
