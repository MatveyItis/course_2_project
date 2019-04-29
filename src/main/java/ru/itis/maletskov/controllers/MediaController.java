package ru.itis.maletskov.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class MediaController {
    @Value("${upload.img.path}")
    private String imgPath;

    @Value("${upload.song.path}")
    private String songPath;

 /*   @GetMapping("/img/{imgFullName:.+}")
    public @ResponseBody
    byte[] getImg(@PathVariable String imgFullName) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(imgPath + imgFullName));
        return IOUtils.toByteArray(bis);
    }
*/
}
