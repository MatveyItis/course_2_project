package ru.itis.maletskov.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.maletskov.services.UsersService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComponentsListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis.maletskov/context.xml");
        UsersService usersService = (UsersService) context.getBean("usersService");

        servletContextEvent.getServletContext().setAttribute("usersService", usersService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
