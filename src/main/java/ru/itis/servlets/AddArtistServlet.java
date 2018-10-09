package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.forms.ArtistForm;
import ru.itis.repositories.ArtistRepository;
import ru.itis.repositories.ArtistRepositoryConnectionImpl;
import ru.itis.services.ArtistService;
import ru.itis.services.ArtistServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddArtistServlet extends HttpServlet {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private ArtistService artistService;

    @Override
    @SneakyThrows
    public void init() {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        ArtistRepository artistRepository = new ArtistRepositoryConnectionImpl(connection);
        artistService = new ArtistServiceImpl(artistRepository);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        getServletContext().getRequestDispatcher("/addArtist.jsp").forward(request, response);
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
