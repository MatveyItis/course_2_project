package ru.itis.maletskov.context;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ApplicationContextPrimitiveImpl implements ApplicationContext {
    private static ApplicationContextPrimitiveImpl context;
    private Map<String, Object> components;
    private static final String CONFIG = "applicationcontext/dbconfig.properties";
    private static final String RESOURCES = "applicationcontext/components.properties";

    static {
        context = new ApplicationContextPrimitiveImpl();
    }

    private ApplicationContextPrimitiveImpl() {
        setComponents();
        setDependencies();
    }

    public static ApplicationContextPrimitiveImpl getContext() {
        return context;
    }

    @Override
    public <T> T getComponent(Class<T> componentClass) {
        for (Object component : components.values()) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                @SuppressWarnings("uncheked")
                T c = (T) component;
                return c;
            }
        }
        return null;
    }

    private void setComponents() {
        components = new HashMap<>();
        setConfig();
        setResources();
    }

    private void setDependencies() {
        for (Map.Entry entry : components.entrySet()) {
            Object obj = entry.getValue();
            for (Field field : obj.getClass().getDeclaredFields()) {
                try {
                    if (!field.getClass().getTypeName().equals(String.class.getTypeName())) {
                        Object dependency = getComponent(field.getType());
                        if (dependency != null) {
                            if (!field.isAccessible()) {
                                field.setAccessible(true);
                                field.set(obj, dependency);
                                field.setAccessible(false);
                            } else {
                                field.set(obj, dependency);
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setConfig() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(CONFIG)) {
            Properties properties = new Properties();
            if (is != null) {
                properties.load(is);
            } else {
                throw new FileNotFoundException();
            }
            DriverManagerDataSource dataSource = new DriverManagerDataSource(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
            dataSource.setDriverClassName(properties.getProperty("driver.class.name"));
            components.put(DataSource.class.getName(), dataSource);
            components.put(JdbcTemplate.class.getName(), new JdbcTemplate(dataSource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setResources() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(RESOURCES)) {
            Properties properties = new Properties();
            if (is != null) {
                properties.load(is);
            }
            for (String resourcePackageName : properties.stringPropertyNames()) {
                List<Class<?>> classes = ClassFinderImpl.getClasses(properties.getProperty(resourcePackageName));
                for (Class<?> aClass : classes) {
                    if (((Class) aClass).getConstructors().length > 0) {
                        components.put(aClass.getName(), ((Class) aClass).newInstance());
                    }
                }
            }
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}