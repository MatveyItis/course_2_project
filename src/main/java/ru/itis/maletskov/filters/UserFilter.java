package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.SongService;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns = {"/profile", "/library", "/admin"})
public class UserFilter implements Filter {
    private UsersService usersService;
    private SongService songService;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        songService = (SongService) context.getAttribute("songService");
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        if (!session.isNew()) {
            String auth = (String) session.getAttribute("authorized");

            if (auth == null || !auth.equals("true")) {
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
            } else if (auth.equals("true")) {
                User user = (User) session.getAttribute("user");
                Library userLibrary = Library.builder()
                        .libraryId(user.getLibrary().getLibraryId())
                        .clientId(user.getClientId())
                        .build();
                userLibrary.setSongs(songService.getSongsByUserId(userLibrary.getClientId()));
                user.setLibrary(userLibrary);
                session.setAttribute("userSongs", user.getLibrary().getSongs() != null ? user.getLibrary().getSongs() : new ArrayList<>());
                if (user.getEmail().equals("maletskovitis@mail.ru")) {
                    session.setAttribute("admin", true);
                    filterChain.doFilter(request, response);
                } else {
                    filterChain.doFilter(request, response);
                }
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
