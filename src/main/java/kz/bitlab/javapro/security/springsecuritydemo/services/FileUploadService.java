package kz.bitlab.javapro.security.springsecuritydemo.services;

import kz.bitlab.javapro.security.springsecuritydemo.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Value("${targetURL}")
    private String targetURL;

    @Autowired
    private UserService userService;

    public boolean uploadUserAva(MultipartFile file, User user) {
        try {
            String fileName = DigestUtils.sha1Hex(user.getId() + "_springSecurity");
            byte bytes[] = file.getBytes();
            Path path = Paths.get(targetURL + "/" + fileName + ".jpg");
            Files.write(path, bytes);

            user.setAvatarUrl(fileName);
            userService.saveUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
