package ru.itis.maletskov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.maletskov.context.Contexts;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.SongService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/library")
public class ActionsWithSongsServlet extends HttpServlet {
    private SongService songService;
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void init() {
        //songService = (SongService) context.getAttribute("songService");
        songService = Contexts.primitive().getComponent(SongService.class);
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/ftl/library.ftl").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Song> currentSongs = user.getLibrary().getSongs();
            Integer songId = Integer.parseInt(req.getParameter("songId"));
            System.out.println(songId);

            session.setAttribute("addingSong", true);

            if (songId != 0) {
                songService.addSongToLibrary(songId, user.getLibrary().getLibraryId());
            }
            currentSongs.add(songService.getSongById(songId));
            String json = objectMapper.writeValueAsString(currentSongs);

            resp.setContentType("application/json");
            resp.getWriter().write(json);
        }
    }

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Я в делите, парни");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Song> currentSongs = user.getLibrary().getSongs();

            Integer songId = Integer.parseInt(req.getParameter("songId"));
            System.out.println(songId);

            session.setAttribute("removingSong", true);

            if (songId != 0) {
                songService.removeSongFromLibrary(songId, user.getLibrary().getLibraryId());
            }
            currentSongs.remove(songService.getSongById(songId));
            String json = objectMapper.writeValueAsString(currentSongs);

            resp.setContentType("application/json");
            resp.getWriter().write(json);
        }
    }
}
