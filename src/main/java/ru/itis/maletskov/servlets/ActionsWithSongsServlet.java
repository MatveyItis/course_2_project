package ru.itis.maletskov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/library")
public class ActionsWithSongsServlet extends HttpServlet {
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void init() {
        //songService = (SongService) context.getAttribute("songService");
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/ftl/library.ftl").forward(req, resp);
    }

    /*@SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Song> currentSongs = user.getLibrary().getUploadedSongs();
        Integer songId = Integer.parseInt(req.getParameter("id"));
        session.setAttribute("addingSong", true);
        songService.addSongToLibrary(songId, user.getLibrary().getId());
        currentSongs.add(songService.getSongById(songId));
        String json = objectMapper.writeValueAsString(currentSongs);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer songId = Integer.parseInt(req.getParameter("id"));
        List<Song> currentSongs = user.getLibrary().getUploadedSongs();
        songService.removeSongFromLibrary(songId, user.getLibrary().getId());
        currentSongs.remove(songService.getSongById(songId));
        session.setAttribute("removingSong", true);
        String json = objectMapper.writeValueAsString(currentSongs);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }*/
}
