package kz.bitlab.javapro.security.springsecuritydemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_comments")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch=FetchType.EAGER)
    private News news;

    @ManyToOne(fetch=FetchType.EAGER)
    private User author;
}
