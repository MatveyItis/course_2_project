package ru.itis.maletskov.filters;

import lombok.SneakyThrows;
import ru.itis.maletskov.context.Contexts;
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
        songService = Contexts.primitive().getComponent(SongService.class);
        artistService = Contexts.primitive().getComponent(ArtistService.class);
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String auth = (String) session.getAttribute("authorized");
        boolean addingSong = session.getAttribute("addingSong") != null && (boolean) session.getAttribute("addingSong");
        boolean removingSong = session.getAttribute("removingSong") != null && (boolean) session.getAttribute("removingSong");
        boolean isHavingSongs = session.getAttribute("userSongs") != null;

        if (auth != null && auth.equals("true")) {
            User user = (User) session.getAttribute("user");
            if (!isHavingSongs | addingSong | removingSong) {
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

                List<Song> allSongs = songService.getAllSongs();

                for (Song song : userSongs) {
                    song.setHaving(true);
                    allSongs.get(song.getSongId() - 1).setHaving(true);
                }

                session.setAttribute("userSongs", userSongs);
                session.setAttribute("songs", allSongs);
                if (addingSong) {
                    session.removeAttribute("addingSong");
                }
                if (removingSong) {
                    session.removeAttribute("removingSong");
                }
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
