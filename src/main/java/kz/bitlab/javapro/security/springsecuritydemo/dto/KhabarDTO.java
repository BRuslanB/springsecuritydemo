package kz.bitlab.javapro.security.springsecuritydemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KhabarDTO {
    private Long id;
    private String khabarTitle;
    private String khabarContent;
}
