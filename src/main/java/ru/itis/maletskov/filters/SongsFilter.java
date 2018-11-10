package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.SongService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebFilter(urlPatterns = {"/library", "/profile"})
public class SongsFilter implements Filter {
    private SongService songService;
    private int count;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        songService = (SongService) context.getAttribute("songService");
        count = 0;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        boolean addingSong = session.getAttribute("addingSong") != null && (boolean) session.getAttribute("addingSong");

        if (count == 0 | addingSong) {
            count++;
            System.out.println("Пошел запрос к бд в " + count + " раз");
            Library userLibrary = Library.builder()
                    .libraryId(user.getLibrary().getLibraryId())
                    .clientId(user.getClientId())
                    .build();
            userLibrary.setSongs(songService.getSongsByUserId(userLibrary.getClientId()));
            user.setLibrary(userLibrary);
            List<Song> userSongs = user.getLibrary().getSongs();
            session.setAttribute("userSongs", userSongs);

            List<Song> allSongs = songService.getAllSongs();

            for (Song song : userSongs) {
                song.setHaving(true);
                allSongs.get(song.getSongId() - 1).setHaving(true);
            }

            session.setAttribute("songs", allSongs);
            session.removeAttribute("addingSong");
        }
        filterChain.doFilter(request, response);
    }

    @SneakyThrows
    @Override
    public void destroy() {

    }
}
