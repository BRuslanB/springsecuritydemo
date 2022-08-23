package kz.bitlab.javapro.security.springsecuritydemo.api;

import kz.bitlab.javapro.security.springsecuritydemo.dto.NewsDTO;
import kz.bitlab.javapro.security.springsecuritydemo.model.News;
import kz.bitlab.javapro.security.springsecuritydemo.model.User;
import kz.bitlab.javapro.security.springsecuritydemo.services.FileUploadService;
import kz.bitlab.javapro.security.springsecuritydemo.services.NewsService;
import kz.bitlab.javapro.security.springsecuritydemo.services.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Value("${uploadURL}")
    private String fileUploadURL;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/enter")
    public String enterPage(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register";
    }

    @PostMapping("/toregister")
    public String toRegister(@RequestParam(name = "user_email") String userEmail,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "user_re_password") String rePassword,
                             @RequestParam(name = "user_full_name") String fullName) {
        if (password.equals(rePassword)) {
            User newUser = new User();
            newUser.setEmail(userEmail);
            newUser.setPassword(password);
            newUser.setFullName(fullName);
            newUser = userService.registerUser(newUser);
            if (newUser != null) {
                return "redirect:/register?success";
            }
        }
        return "redirect:/register?error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updatepassword")
    public String updatePassword(@RequestParam(name = "old_password") String oldPassword,
                                 @RequestParam(name = "new_password") String newPassword,
                                 @RequestParam(name = "re_new_password") String repeatNewPassword) {

        if (newPassword.equals(repeatNewPassword)) {
            User updatePassword = userService.updatePassword(getCurrentUser(), oldPassword, newPassword);
            if (updatePassword != null) {
                return "redirect:/profile?success";
            }
        }
        return "redirect:/profile?error";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profilePage(Model model) {
        return "profile";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/adminpanel")
    public String adminPage(Model model) {
        return "admin";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/news")
    public String newsPage(Model model) {
        List<NewsDTO> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        return "news";
    }

    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @GetMapping("/teacherpanel")
    public String teacherPage(Model model) {
        return "teacher";
    }

    @GetMapping("/forbidden")
    public String forbiddenPage(Model model) {
        return "403";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/addnews")
    public String addNews(@RequestParam(name = "title") String title,
                          @RequestParam(name = "content") String content) {

        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setPostDate(new Date());
        news.setAuthor(getCurrentUser());
        newsService.addNews(news);
        return "redirect:/news";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/uploadava")
    public String uploadAva(@RequestParam(name = "user_avatar") MultipartFile file){
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")){
            fileUploadService.uploadUserAva(file, getCurrentUser());
        }
        return "redirect:/profile";
    }

    @GetMapping(value = "/avatar/{url}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] avatar(@PathVariable(name = "url", required = false) String url) throws IOException{
        String picURL = fileUploadURL + "default.jpg";
        if (url != null){
            picURL = fileUploadURL +  url + ".jpg";
        }
        InputStream in;
        try{
            ClassPathResource classPathResource = new ClassPathResource(picURL);
            in = classPathResource.getInputStream();
        }catch(Exception e){
            picURL = fileUploadURL + "default.jpg";
            ClassPathResource classPathResource = new ClassPathResource(picURL);
            in = classPathResource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }
}