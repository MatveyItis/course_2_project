package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
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

            }
            if (!isAuthorized) {
                request.getRequestDispatcher("/WEB-INF/ftl/registration.ftl").forward(request, response);
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
