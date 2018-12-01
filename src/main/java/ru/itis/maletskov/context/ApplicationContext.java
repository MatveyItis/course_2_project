package ru.itis.maletskov.context;

public interface ApplicationContext {
    <T> T getComponent(Class<T> componentClass);
}
