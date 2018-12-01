package ru.itis.maletskov.context;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.maletskov.repositories.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContextPrimitiveImpl implements ApplicationContext {
    private static ApplicationContextPrimitiveImpl context;
    private Map<String, Object> components;

    static {
        context = new ApplicationContextPrimitiveImpl();
    }

    private ApplicationContextPrimitiveImpl() {
        components = new HashMap<>();

        components.put("URL", "jdbc:postgresql://localhost:5432/musicservice");
        components.put("USERNAME", "postgres");
        components.put("PASSWORD", "12ER56ui78");
        components.put("driverClassName", "org.postgresql.Driver");
        components.put("encoder", new BCryptPasswordEncoder());

        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                (String) components.get("URL"), (String) components.get("USERNAME"), (String) components.get("PASSWORD"));
        dataSource.setDriverClassName((String) components.get("driverClassName"));
        components.put("dataSource", dataSource);

        components.put("usersRepository", new UsersRepositoryJdbcTemplateImpl((DataSource) components.get("dataSource")));
        components.put("songRepository", new SongRepositoryJdbcTemplateImpl((DataSource) components.get("dataSource")));
        components.put("libraryRepository", new LibraryRepositoryJdbcTemplateImpl((DataSource) components.get("dataSource")));
        components.put("authRepository", new AuthRepositoryJdbcTemplateImpl((DataSource) components.get("dataSource")));
        components.put("artistRepository", new ArtistRepositoryJdbcTemplateImpl((DataSource) components.get("dataSource")));
        components.put("albumRepository", new AlbumRepositoryJdbcTemplateImpl((DataSource) components.get("dataSource")));
    }

    public static ApplicationContextPrimitiveImpl getContext() {
        return context;
    }

    @Override
    public <T> T getComponent(Class<T> componentClass) {
        for (Object component : components.values()) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                return (T) component;
            }
        }
        return null;
    }

    @SneakyThrows
    private <T> T setComponentsForService(Class serviceClass) {

        return null;
    }
}