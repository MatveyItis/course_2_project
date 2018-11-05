package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile", "/library"})
public class UserFilter implements Filter {
    private UsersService usersService;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        if (!session.isNew()) {
            String auth = (String) session.getAttribute("authorized");
            System.out.println(auth);

            if (auth == null || !auth.equals("true")) {
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
        }
    }

    @SneakyThrows
    @Override
    public void destroy() {

    }
}