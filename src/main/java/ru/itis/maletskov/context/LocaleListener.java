package ru.itis.maletskov.context;

import ru.itis.maletskov.localization.Localizations;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

public class LocaleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Map<String, String> localeEn = Localizations.loadLocalization("en");
        Map<String, String> localeRu = Localizations.loadLocalization("ru");
        context.setAttribute("localeEn", localeEn);
        context.setAttribute("localeRu", localeRu);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
