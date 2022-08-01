package kz.bitlab.javapro.security.springsecuritydemo.mapper;

import kz.bitlab.javapro.security.springsecuritydemo.dto.CommentDTO;
import kz.bitlab.javapro.security.springsecuritydemo.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
            @Mapping(target = "newsId", source = "news.id")
    })
    CommentDTO toDto(Comment comment);

    @Mappings({
            @Mapping(target = "news.id", source = "newsId")
    })
    Comment toEntity(CommentDTO commentDTO);

    List<CommentDTO> toList(List<Comment> comments);
}
