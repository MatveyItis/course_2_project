package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@WebFilter(urlPatterns = {"/profile", "/library", "/admin"})
public class UserFilter implements Filter {
    private UsersService usersService;
    boolean isAuthorized;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        isAuthorized = false;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String auth = (String) session.getAttribute("authorized");

        if (auth == null || !auth.equals("true")) {
            isAuthorized = false;
            if (request.getCookies() != null) {
                Cookie jsessionid = null, uid = null, uid2 = null;
                for (Cookie cookie : request.getCookies()) {
                    switch (cookie.getName()) {
                        case "JSESSIONID":
                            jsessionid = cookie;
                            break;
                        case "uid":
                            uid = cookie;
                            break;
                        case "uid2":
                            uid2 = cookie;
                            break;
                    }
                }
                if (jsessionid != null && uid != null && uid2 != null) {
                    jsessionid.setValue(uid.getValue());
                    Optional<User> userOptional = usersService.getUsersRepository()
                            .findOne(Integer.parseInt(uid2.getValue()));
                    if (userOptional.isPresent()) {
                        session.setAttribute("user", userOptional.get());
                        session.setAttribute("authorized", "true");
                        isAuthorized = true;
                        filterChain.doFilter(request, response);
                    }
                }
            }
            if (!isAuthorized) {
                request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
            }
        } else if (auth.equals("true")) {
            isAuthorized = true;
            filterChain.doFilter(request, response);
        }
    }

    @SneakyThrows
    @Override
    public void destroy() {

    }
}
