package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.SongService;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private UsersService usersService;
    private SongService songService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        songService = (SongService) context.getAttribute("songService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
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
            session.setAttribute("authorized", "true");
            User user = usersService.getUsersRepository().findOneByEmail(loginForm.getEmail()).get();
            session.setAttribute("user", user);
            if (rememberMe != null && rememberMe.equals("on")) {
                Cookie userCookie = new Cookie("uid", session.getId());
                userCookie.setMaxAge(60 * 60 * 24);
                Cookie userIdCookie = new Cookie("uid2", user.getClientId().toString());
                userIdCookie.setMaxAge(60 * 60 * 24);
                resp.addCookie(userCookie);
                resp.addCookie(userIdCookie);
            }
            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/signUp");
        }
    }
}
