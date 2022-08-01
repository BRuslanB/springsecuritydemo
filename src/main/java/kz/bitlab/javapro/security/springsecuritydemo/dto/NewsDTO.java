package kz.bitlab.javapro.security.springsecuritydemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private Date postDate;
    private AuthorDTO author;
}
