package model.repository;

import model.entity.Article;
import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by psawz on 19.04.2017.
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findByIdArticle(Integer id);
    List<Article> findAll();
    Optional<List<Article>> findByDate(Date date);
    Optional<List<Article>> findByUser(User user);
    Optional<List<Article>> findFirst5ByOrderByIdArticleDesc();
}
