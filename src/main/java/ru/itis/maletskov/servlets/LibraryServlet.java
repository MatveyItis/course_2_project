package ru.itis.maletskov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.SongService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/views/library.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Song> currentSongs = user.getLibrary().getSongs();
            Integer songId = Integer.parseInt(req.getParameter("songId"));

            session.setAttribute("addingSong", true);

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
}
