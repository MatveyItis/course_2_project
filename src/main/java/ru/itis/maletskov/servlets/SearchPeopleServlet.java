package ru.itis.maletskov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/profile/search")
public class SearchPeopleServlet extends HttpServlet {
    private UsersService usersService;
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        String userName = req.getParameter("userName");

        if (session.getAttribute("user") != null && userName != null && !userName.equals("")) {
            List<User> users = usersService.searchPeople(userName);

            if (users.size() != 0) {
                String json = objectMapper.writeValueAsString(users);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                resp.getWriter().write(json);
                users.clear();
            }
        }
    }
}
