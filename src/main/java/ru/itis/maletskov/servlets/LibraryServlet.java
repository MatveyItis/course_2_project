package ru.itis.maletskov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.SongService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/library")
public class LibraryServlet extends HttpServlet {
    private SongService songService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        songService = (SongService) context.getAttribute("songService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/library.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user);

        List<Song> currentSongs = songService.getSongsByUserId(user.getClientId());
        System.out.println(Arrays.toString(currentSongs.toArray()));

        Integer songId = Integer.parseInt(req.getParameter("songId"));
        System.out.println(songId);
        if (songId != 0) {
            songService.addSongToLibrary(songId, user.getLibrary().getLibraryId());
        }
        currentSongs.add(songService.getSongById(songId));
        String json = objectMapper.writeValueAsString(currentSongs);
        System.out.println(json);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
