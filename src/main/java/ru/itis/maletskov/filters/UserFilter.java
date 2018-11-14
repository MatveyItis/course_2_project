package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.SongService;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/profile", "/library", "/admin"})
public class UserFilter implements Filter {
    private UsersService usersService;
    private SongService songService;
    boolean isAuthorized;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        songService = (SongService) context.getAttribute("songService");
        isAuthorized = false;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        boolean isOldUser = false;
        if (!isAuthorized) {
            if (!session.isNew()) {
                String auth = (String) session.getAttribute("authorized");
                System.out.println("authorized = " + auth);

                if (request.getCookies() != null) {
                    Cookie jsessionid = null;
                    //jsessionid
                    Cookie uid = null;
                    //user id
                    Cookie uid2 = null;
                    for (Cookie cookie : request.getCookies()) {
                        if (cookie.getName().equals("JSESSIONID")) {
                            jsessionid = cookie;
                        } else if (cookie.getName().equals("uid")) {
                            uid = cookie;
                        } else if (cookie.getName().equals("uid2")) {
                            uid2 = cookie;
                        }
                    }
                    if (jsessionid != null && uid != null && uid2 != null) {
                        jsessionid.setValue(uid.getValue());
                        Optional<User> userOptional = usersService.getUsersRepository()
                                .findOne(Integer.parseInt(uid2.getValue()));
                        if (userOptional.isPresent()) {
                            isOldUser = true;
                            session.setAttribute("user", userOptional.get());
                            session.setAttribute("authorized", "true");
                            auth = "true";
                            isAuthorized = true;
                        }
                        filterChain.doFilter(request, response);
                    }
                }

                if (auth == null || !auth.equals("true")) {
                    isAuthorized = false;
                    request.getServletContext().getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
                } else if (!isOldUser) {
                    User user = (User) session.getAttribute("user");
                    isAuthorized = true;
                    if (user.getEmail().equals("maletskovitis@mail.ru")) {
                        session.setAttribute("admin", true);
                        filterChain.doFilter(request, response);
                    } else {
                        filterChain.doFilter(request, response);
                    }
                }
            } else if (request.getSession().getAttribute("authorized") == null) {
                isAuthorized = false;
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @SneakyThrows
    @Override
    public void destroy() {

    }
}
