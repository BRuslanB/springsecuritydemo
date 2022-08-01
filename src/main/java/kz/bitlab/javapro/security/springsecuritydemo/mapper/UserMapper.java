package kz.bitlab.javapro.security.springsecuritydemo.mapper;

import kz.bitlab.javapro.security.springsecuritydemo.dto.AuthorDTO;
import kz.bitlab.javapro.security.springsecuritydemo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AuthorDTO toDto(User user);
    User toEntity(AuthorDTO authorDTO);
}
