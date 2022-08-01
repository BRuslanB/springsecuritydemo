package kz.bitlab.javapro.security.springsecuritydemo.api;

import kz.bitlab.javapro.security.springsecuritydemo.dto.NewsDTO;
import kz.bitlab.javapro.security.springsecuritydemo.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final NewsService newsService;

    @GetMapping("/")
    public String indexPage(Model model) {
        List<NewsDTO> allNews= newsService.getAllNews();
        model.addAttribute("allNews", allNews);
        return "index";
    }

    @GetMapping("/details/{newsId}")
    public String detailsPage(@PathVariable(name="newsId") Long newsId,
                               Model model){
        NewsDTO news = newsService.getNews(newsId);
        model.addAttribute("news", news);
        return "details";
    }
}
