package kz.bitlab.javapro.security.springsecuritydemo.repository;

import kz.bitlab.javapro.security.springsecuritydemo.model.Comment;
import kz.bitlab.javapro.security.springsecuritydemo.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByNews(News news);
    List<Comment> findAllByNews_Id(Long id);
}
