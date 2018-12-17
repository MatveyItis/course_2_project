package ru.itis.maletskov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.maletskov.config.JavaConfig;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/profile/search")
public class SearchPeopleServlet extends HttpServlet {
    private UsersService usersService;
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        usersService = context.getBean(UsersService.class);
        /*usersService = Contexts.primitive().getComponent(UsersService.class);*/
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getServletContext().getRequestDispatcher("/WEB-INF/ftl/profile.ftl").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName");
        if (!userName.equals("")) {
            List<User> users = usersService.searchPeople(userName);
            String json = objectMapper.writeValueAsString(users);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            resp.getWriter().write(json);
            users.clear();
        }
    }
}
