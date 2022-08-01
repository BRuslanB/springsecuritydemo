package kz.bitlab.javapro.security.springsecuritydemo.mapper;

import kz.bitlab.javapro.security.springsecuritydemo.dto.KhabarDTO;
import kz.bitlab.javapro.security.springsecuritydemo.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KhabarMapper {

    @Mappings({
            @Mapping(target = "khabarTitle", source = "title"),
            @Mapping(target = "khabarContent", source = "content")
    })
    KhabarDTO toDto(News news);
    List<KhabarDTO> toDtoList(List<News> newsList);
}
