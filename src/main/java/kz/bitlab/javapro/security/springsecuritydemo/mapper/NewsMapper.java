package kz.bitlab.javapro.security.springsecuritydemo.mapper;

import kz.bitlab.javapro.security.springsecuritydemo.dto.NewsDTO;
import kz.bitlab.javapro.security.springsecuritydemo.model.News;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsDTO toDto(News news);
    News toEntity(NewsDTO newsDTO);
    List<NewsDTO> toDtoList(List<News> newsList);
}
