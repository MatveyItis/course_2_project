package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.models.Artist;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.services.SongService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@WebServlet("/uploadSong")
@MultipartConfig
public class UploadSongServlet extends HttpServlet {
    private SongService songService;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        songService = (SongService) context.getAttribute("songService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/ftl/admin.ftl").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String songTitle = req.getParameter("song-title");
        Integer artistId = Integer.parseInt(req.getParameter("artist"));

        @SuppressWarnings("unchecked")
        List<Artist> artists = (List<Artist>) req.getSession().getAttribute("artists");

        Part filePart = req.getPart("song-file");

        InputStream fileContent = filePart.getInputStream();
        byte[] arrays = fileContent.readAllBytes();
        String filePath = "/Users/matveymaletskov/IdeaProjects/course_2_project/src/main/webapp/audiofiles/" + artists.get(artistId - 1).getNickname() + "_" + songTitle + ".mp3";
        String relativeFilePath = "/audiofiles/" + artists.get(artistId - 1).getNickname() + "_" + songTitle + ".mp3";

        File mp3file = new File(filePath);
        mp3file.createNewFile();
        FileOutputStream fos = new FileOutputStream(mp3file);
        fos.write(arrays);

        Song song = Song.builder()
                .title(songTitle)
                .artist(Artist.builder()
                        .artistId(artistId)
                        .build())
                .songSrc(relativeFilePath)
                .build();

        songService.saveSong(song);

        resp.setContentLength(arrays.length);
        resp.sendRedirect("/admin");
    }
}
