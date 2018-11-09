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
import java.io.PrintWriter;
import java.util.ArrayList;
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

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/views/library.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user);

        List<Song> currentSongs = songService.getSongsByUserId(user.getClientId());
        if (currentSongs.size() == 1 & currentSongs.get(0).getSongId() == 0) {
            currentSongs = new ArrayList<>();
        }
        System.out.println(Arrays.toString(currentSongs.toArray()));

        Integer songId = Integer.parseInt(req.getParameter("songId"));
        for (Song song : currentSongs) {
            if (songId.equals(song.getSongId())) {
                PrintWriter printWriter = resp.getWriter();
                printWriter.write("<script type='text/javascript'>");
                printWriter.write("alert('Песня уже есть в вашей библиотеке')");
                printWriter.write("</script>");
                break;
            }
        }
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
