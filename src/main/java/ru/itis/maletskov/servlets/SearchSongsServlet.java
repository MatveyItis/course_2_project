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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/library/search")
public class SearchSongsServlet extends HttpServlet {
    private SongService songService;
    private List<Song> songs;
    private List<Song> currentSongs;
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        songService = (SongService) context.getAttribute("songService");
        songs = songService.getAllSongs();
        objectMapper = new ObjectMapper();
        currentSongs = new ArrayList<>();
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

        if (currentSongs.size() != 0) {
            currentSongs.clear();
        }

        String name = req.getParameter("songName");
        System.out.println("song name = " + name);

        if (user != null && name != null && !name.equals("")) {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getTitle().contains(name) ||
                        songs.get(i).getArtist().getNickname().contains(name)) {
                    currentSongs.add(songs.get(i));
                }
            }

            String json;
            if (currentSongs.size() != 0) {
                json = objectMapper.writeValueAsString(currentSongs);
            } else {
                String notFound = "Nothing found!";
                json = objectMapper.writeValueAsString(notFound);
            }
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            resp.getWriter().write(json);
        }
    }
}