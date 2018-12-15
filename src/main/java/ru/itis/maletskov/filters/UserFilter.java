package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.context.Contexts;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/profile", "/library", "/admin"})
public class UserFilter implements Filter {
    private UsersService usersService;
    private boolean isAuthorized;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) {
        usersService = Contexts.primitive().getComponent(UsersService.class);
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
                Cookie jsessionid = null, uid = null;
                for (Cookie cookie : request.getCookies()) {
                    switch (cookie.getName()) {
                        case "JSESSIONID":
                            jsessionid = cookie;
                            break;
                        case "uuid":
                            uid = cookie;
                            break;
                    }
                }
                if (jsessionid != null && uid != null) {
                    Integer userId = usersService.getUserIdByCookieValue(uid.getValue());
                    if (userId != 0) {
                        jsessionid.setValue(uid.getValue());
                        User user = usersService.findOneById(userId);
                        if (user != null) {
                            session.setAttribute("user", user);
                            session.setAttribute("authorized", "true");
                            if (user.getEmail().equals("maletskovitis@mail.ru")) {
                                session.setAttribute("admin", true);
                            }
                            isAuthorized = true;
                        }
                    } else {
                        isAuthorized = false;
                    }
                    filterChain.doFilter(request, response);
                }
            }
            if (!isAuthorized) {
                request.getRequestDispatcher("/WEB-INF/ftl/signUp.ftl").forward(request, response);
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
