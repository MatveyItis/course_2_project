package ru.itis.maletskov.context;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassFinderImpl {
    @SneakyThrows
    public static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        List<File> allFiles = new ArrayList<>();
        String pckgName = packageName.replace('.', '/');
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(pckgName);
        if (url == null) {
            throw new IllegalArgumentException();
        }
        File directory = new File(url.getFile());
        if (directory.isDirectory()) {
            List<File> files = Arrays.asList(directory.listFiles());
            allFiles.addAll(files);
            for (File file : allFiles) {
                String className = file.getPath().replace('/', '.');
                className = className.substring(1, className.length());
                className = className.substring(className.indexOf("ru"));
                className = className.substring(0, className.indexOf(".class"));
                Class c = Class.forName(className);
                classes.add(c);
            }
        }
        return classes;
    }
}
