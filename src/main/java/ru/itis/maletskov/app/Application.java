package ru.itis.maletskov.app;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Application {
    @SneakyThrows
    public static void main(String[] args) {
        /*Socket s = new Socket("java-course.ru", 80);

        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        String str = "GET /network.txt HTTP/1.1\r\n" +
                "Host:java-course.ru\r\n\r\n";
        byte buf[] = str.getBytes();
        out.write(buf);

        int size;
        byte buf_out[] = new byte[1024];
        while ((size = in.read(buf_out)) != -1) {
            System.out.print(new String(buf_out, 0, size));
        }
        s.close();*/


        String query = "http://localhost:8080/home";

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(query).openConnection();

            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setConnectTimeout(300);
            connection.setReadTimeout(300);

            File file = new File("/Users/matveymaletskov/IdeaProjects/course_2_project/src/main/webapp/images/avatarcat.png");
            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                System.out.println(sb.toString());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
