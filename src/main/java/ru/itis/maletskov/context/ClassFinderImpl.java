package ru.itis.maletskov.context;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class ClassFinderImpl implements ClassFinder {
    @SneakyThrows
    @Override
    public List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        List<File> allFiles = new ArrayList<>();
        File directory = new File(packageName);
        if (directory.isDirectory()) {
            List<File> files = Arrays.asList(directory.listFiles());
            allFiles.addAll(files);
            for (File file : allFiles) {
                String className = file.getCanonicalPath().replace('/', '.');
                className = className.substring(className.indexOf("ru"));
                className = className.substring(0, className.indexOf(".java"));
                Class c = Class.forName(className);
                classes.add(c);
            }
        }
        return classes;
    }
}
