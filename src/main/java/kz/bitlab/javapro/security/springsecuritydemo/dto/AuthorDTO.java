package kz.bitlab.javapro.security.springsecuritydemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String email;
    private String fullName;
}
