package kz.bitlab.javapro.security.springsecuritydemo.repository;

import kz.bitlab.javapro.security.springsecuritydemo.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {

}
