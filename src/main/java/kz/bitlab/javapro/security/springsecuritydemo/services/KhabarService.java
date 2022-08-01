package kz.bitlab.javapro.security.springsecuritydemo.services;

import kz.bitlab.javapro.security.springsecuritydemo.dto.KhabarDTO;
import kz.bitlab.javapro.security.springsecuritydemo.mapper.KhabarMapper;
import kz.bitlab.javapro.security.springsecuritydemo.model.News;
import kz.bitlab.javapro.security.springsecuritydemo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhabarService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private KhabarMapper khabarMapper;

    public List<KhabarDTO> getAllKhabars(){
        return khabarMapper.toDtoList(newsRepository.findAll());
    }

    public News addNews(News news){
        return newsRepository.save(news);
    }

    public KhabarDTO getKhabar(Long id){
        return khabarMapper.toDto(newsRepository.findById(id).orElseThrow());
    }

}
