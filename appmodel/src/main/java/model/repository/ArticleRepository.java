package model.repository;

import model.entity.Article;
import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by psawz on 19.04.2017.
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Article findByIdArticle(Integer id);
    List<Article> findAll();
    List<Article> findByDate(Date date);
    List<Article> findByUser(User user);
}
