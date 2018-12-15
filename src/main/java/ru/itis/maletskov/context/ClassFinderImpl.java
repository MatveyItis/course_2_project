package ru.itis.maletskov.context;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class ClassFinderImpl {
    @SneakyThrows
    public static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        List<File> allFiles = new ArrayList<>();
        File directory = new File(packageName);
        if (directory.isDirectory()) {
            List<File> files = Arrays.asList(directory.listFiles());
            allFiles.addAll(files);
            for (File file : allFiles) {
                String className = file.getPath().replace('/', '.');
                className = className.substring(1, className.length());
                className = className.substring(className.indexOf("ru"));
                className = className.substring(0, className.indexOf(".java"));
                Class c = Class.forName(className);
                classes.add(c);
            }
        }
        return classes;
    }
}
