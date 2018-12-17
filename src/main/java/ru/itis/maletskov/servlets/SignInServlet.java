package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.maletskov.config.JavaConfig;
import ru.itis.maletskov.forms.AuthForm;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.UUID;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init() {
        /*ApplicationContext context = Contexts.primitive();
        usersService = context.getComponent(UsersService.class);*/
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        usersService = context.getBean(UsersService.class);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher("/WEB-INF/ftl/signUp.ftl").forward(request, response);
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
                UUID uuid = UUID.randomUUID();
                Cookie cookieUuid = new Cookie("uuid", uuid.toString());
                cookieUuid.setMaxAge(60 * 60 * 240);
                resp.addCookie(cookieUuid);
                usersService.auth(AuthForm.builder()
                        .uuid(uuid)
                        .clientId(user.getClientId())
                        .build());
            }
            resp.sendRedirect(req.getServletContext().getContextPath() + "/profile");
        } else {
            resp.sendRedirect(req.getServletContext().getContextPath() + "/signUp");
        }
    }
}
