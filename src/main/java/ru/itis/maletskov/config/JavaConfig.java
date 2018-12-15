package ru.itis.maletskov.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.maletskov.repositories.*;
import ru.itis.maletskov.services.*;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JavaConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.class.name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public SongRepository songRepository() {
        return new SongRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public LibraryRepository libraryRepository() {
        return new LibraryRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public AuthRepository authRepository() {
        return new AuthRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public ArtistRepository artistRepository() {
        return new ArtistRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public AlbumRepository albumRepository() {
        return new AlbumRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository(), authRepository());
    }

    @Bean
    public SongService songService() {
        return new SongServiceImpl(songRepository(),libraryRepository());
    }

    @Bean
    public ArtistService artistService() {
        return new ArtistServiceImpl(artistRepository());
    }
}