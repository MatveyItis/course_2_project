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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile/search")
public class SearchPeopleServlet extends HttpServlet {
    private UsersService usersService;
    private List<User> users;
    private List<User> currentUsers;
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        users = usersService.getUsersRepository().findAll().get();
        objectMapper = new ObjectMapper();
        currentUsers = new ArrayList<>();
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

        if (currentUsers.size() != 0) {
            currentUsers.clear();
        }

        String userName = req.getParameter("userName");
        System.out.println("username = " + userName);

        if (session.getAttribute("user") != null && userName != null && !userName.equals("")) {
            for (int i = 0; i < users.size(); i++) {
                if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(users.get(i).getFirstName(), userName)
                        || org.apache.commons.lang3.StringUtils.containsIgnoreCase(users.get(i).getLastName(), userName)) {
                    currentUsers.add(users.get(i));
                }
            }

            if (currentUsers.size() != 0) {
                String json = objectMapper.writeValueAsString(currentUsers);

                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                resp.getWriter().write(json);
                currentUsers.clear();
            }
        }
    }
}
