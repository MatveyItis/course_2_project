package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/profile", "/library", "/admin", "/signUp"})
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
                Cookie jsessionid = null, uid = null;
                for (Cookie cookie : request.getCookies()) {
                    switch (cookie.getName()) {
                        case "JSESSIONID":
                            jsessionid = cookie;
                            break;
                        case "uid":
                            uid = cookie;
                            break;
                    }
                }
                if (jsessionid != null && uid != null) {
                    Integer userId = usersService.getUserIdByCookieValue(uid.getValue());
                    if (userId != 0) {
                        jsessionid.setValue(uid.getValue());
                        session.setAttribute("user", usersService.getUsersRepository().findOne(userId).get());
                        session.setAttribute("authorized", "true");
                        isAuthorized = true;
                    } else {
                        isAuthorized = false;
                    }
                    filterChain.doFilter(request, response);
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
