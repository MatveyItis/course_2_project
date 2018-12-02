package ru.itis.maletskov.context;

import java.util.List;

public interface ClassFinder {
    List<Class<?>> getClasses(String packageName);
}
