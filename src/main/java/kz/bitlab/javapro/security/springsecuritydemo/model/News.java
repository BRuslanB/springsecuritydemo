package kz.bitlab.javapro.security.springsecuritydemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_news")
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="post_date")
    private Date postDate;

    @ManyToOne(fetch=FetchType.EAGER)
    private User author;
}
