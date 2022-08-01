package kz.bitlab.javapro.security.springsecuritydemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String comment;
    private AuthorDTO author;
    private Long newsId;
}
