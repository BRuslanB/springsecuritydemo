package kz.bitlab.javapro.security.springsecuritydemo.services;

import kz.bitlab.javapro.security.springsecuritydemo.dto.NewsDTO;
import kz.bitlab.javapro.security.springsecuritydemo.mapper.NewsMapper;
import kz.bitlab.javapro.security.springsecuritydemo.model.News;
import kz.bitlab.javapro.security.springsecuritydemo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    public List<NewsDTO> getAllNews(){
        return newsMapper.toDtoList(newsRepository.findAll());
    }

    public News addNews(News news){
        return newsRepository.save(news);
    }

    public NewsDTO getNews(Long id){
        return newsMapper.toDto(newsRepository.findById(id).orElseThrow());
    }

//    private NewsDTO mapNewsToDto(News news){
//        NewsDTO newsDTO = new NewsDTO();
//        newsDTO.setId(news.getId());
//        newsDTO.setTitle(news.getTitle());
//        newsDTO.setContent(news.getContent());
//        newsDTO.setPostDate(news.getPostDate());
//        newsDTO.setAuthor(mapUserToDto(news.getAuthor()));
//        return newsDTO;
//    }
//
//    private AuthorDTO mapUserToDto(User user){
//        AuthorDTO authorDTO = new AuthorDTO();
//        authorDTO.setId(user.getId());
//        authorDTO.setEmail(user.getEmail());
//        authorDTO.setFullName(user.getFullName());
//        return authorDTO;
//    }
}
