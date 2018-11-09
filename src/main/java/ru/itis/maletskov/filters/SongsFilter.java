package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.services.SongService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/library"})
public class SongsFilter implements Filter {
    private SongService songService;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        songService = (SongService) context.getAttribute("songService");
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        if (session.getAttribute("songs") == null) {
            session.setAttribute("songs", songService.getAllSongs());
        }

        filterChain.doFilter(request, response);
    }

    @SneakyThrows
    @Override
    public void destroy() {

    }
}
