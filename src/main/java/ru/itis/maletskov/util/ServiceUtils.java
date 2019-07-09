package ru.itis.maletskov.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.maletskov.model.Img;
import ru.itis.maletskov.model.Song;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ServiceUtils {
    @Value("${upload.song.path}")
    private String audioUploadPath;

    @Value("${upload.img.path}")
    private String imgUploadPath;


    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public void saveAudioFile(Song song, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty() && !file.isEmpty()) {
            new File(audioUploadPath).mkdir();
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + FilenameUtils.getExtension(file.getOriginalFilename());
            file.transferTo(new File(audioUploadPath + "/" + resultFilename));
            song.setAudioFileName(resultFilename);
        }
    }

    public Img saveImageFile(MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty() && !file.isEmpty()) {
            new File(imgUploadPath).mkdir();
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + FilenameUtils.getExtension(file.getOriginalFilename());
            file.transferTo(new File(imgUploadPath + "/" + resultFilename));
            Img img = new Img();
            img.setFileName(resultFilename);
            return img;
        }
        return null;
    }
}