package kz.bitlab.javapro.security.springsecuritydemo.rest;

import kz.bitlab.javapro.security.springsecuritydemo.dto.NewsDTO;
import kz.bitlab.javapro.security.springsecuritydemo.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.LinkOption;
import java.util.List;

@RestController
@RequestMapping(value = "/api/news")
@CrossOrigin
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping         //(value = "/allnews")
    public ResponseEntity<List<NewsDTO>> getAllNews(){
        return new ResponseEntity<>(newsService.getAllNews(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")         //(value = "/getnews{id}")
    public ResponseEntity<NewsDTO> getNews(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(newsService.getNews(id), HttpStatus.OK);
    }
}
