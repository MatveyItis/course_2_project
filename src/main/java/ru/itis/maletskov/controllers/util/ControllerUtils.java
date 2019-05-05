package ru.itis.maletskov.controllers.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.maletskov.models.Img;
import ru.itis.maletskov.models.Song;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ControllerUtils {
    @Value("${upload.song.path}")
    private static String audioUploadPath;

    @Value("${upload.img.path}")
    private static String imgUploadPath;


    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public static void saveAudioFile(Song song, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty() && !file.isEmpty()) {
            new File(audioUploadPath).mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + file.getOriginalFilename();

            file.transferTo(new File(audioUploadPath + "/" + resultFilename));

            song.setAudioFileName(resultFilename);
        }
    }

    public static Img saveImageFile(MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty() && !file.isEmpty()) {
            new File(imgUploadPath).mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + file.getOriginalFilename();

            file.transferTo(new File(imgUploadPath + "/" + resultFilename));

            Img img = new Img();
            img.setFileName(resultFilename);
            return img;
        }
        return null;
    }
}
