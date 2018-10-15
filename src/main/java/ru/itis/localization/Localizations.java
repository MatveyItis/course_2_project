package ru.itis.localization;

import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Localizations {
    @SneakyThrows
    public static Map<String, String> loadLocalization(String locale) {
        Scanner scanner = new Scanner(new File("/Users/matveymaletskov/" +
                "IdeaProjects/course_2_project/src/main/resources/messages/messages_" +
                locale + ".properties"));
        Map<String, String> localeMap = new HashMap<>();

        while (scanner.hasNext()) {
            String currentValue = scanner.nextLine();
            String[] localValues = currentValue.split("=");
            localeMap.put(localValues[0], localValues[1]);
        }

        return localeMap;
    }
}
