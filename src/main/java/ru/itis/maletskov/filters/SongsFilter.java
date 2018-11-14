package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.models.Library;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.ArtistService;
import ru.itis.maletskov.services.SongService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebFilter(urlPatterns = {"/library", "/profile", "/admin"})
public class SongsFilter implements Filter {
    private SongService songService;
    private ArtistService artistService;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        songService = (SongService) context.getAttribute("songService");
        artistService = (ArtistService) context.getAttribute("artistService");
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String auth = (String) session.getAttribute("authorized");
        boolean addingSong = session.getAttribute("addingSong") != null && (boolean) session.getAttribute("addingSong");
        boolean isHavingSongs = session.getAttribute("userSongs") != null;


        if (auth.equals("true")) {
            if (!isHavingSongs | addingSong) {
                User user = (User) session.getAttribute("user");
                System.out.println("зашел в добавление песен");
                if (!isHavingSongs) {
                    session.setAttribute("artists", artistService.getAllArtists());
                }
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
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @SneakyThrows
    @Override
    public void destroy() {

    }
}
