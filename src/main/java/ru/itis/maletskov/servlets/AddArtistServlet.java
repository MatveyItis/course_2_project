package ru.itis.maletskov.servlets;

import lombok.SneakyThrows;
import ru.itis.maletskov.forms.ArtistForm;
import ru.itis.maletskov.services.ArtistService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddArtistServlet extends HttpServlet {
    private ArtistService artistService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        artistService = (ArtistService)context.getAttribute("artistService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        getServletContext().getRequestDispatcher("/views/addArtist.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String nickname = request.getParameter("nickname");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthday = request.getParameter("birthday");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String genreName = request.getParameter("genreName");
        ArtistForm artistForm = ArtistForm.builder()
                .nickname(nickname)
                .firstName(firstName)
                .lastName(lastName)
                .birthday(sqlDate)
                .genreName(genreName)
                .build();
        artistService.addArtist(artistForm);
    }
}
