package ru.itis.maletskov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.maletskov.context.Contexts;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.services.SongService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/library/search")
public class SearchSongsServlet extends HttpServlet {
    private SongService songService;
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {
        songService = Contexts.primitive().getComponent(SongService.class);
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/views/library.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("songName");
        List<Song> listSongs = songService.searchSongs(name);
        String json = objectMapper.writeValueAsString(listSongs);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

}