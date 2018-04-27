package model.dao;

import model.entity.Article;
import model.entity.User;
import model.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 * Created by psawz on 09.05.2017.
 */
@Repository
public class ArticleRepositoryDAO {

    @Autowired
    private ArticleRepository articleRepository;

    public Article getArticlesById(Integer id) {
        return articleRepository.findByIdArticle(id).orElseThrow(NoResultException::new);
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public List<Article> getArticlesByUser(User user) {
        return articleRepository.findByUser(user).orElseThrow(NoResultException::new);
    }

    public List<Article> getArticlesByDate(Date date) {
        return articleRepository.findByDate(date).orElseThrow(NoResultException::new);
    }

    public List<Article> getLastFiveArticles() {
        return articleRepository.findFirst5ByOrderByIdArticleDesc().orElseThrow(NoResultException::new);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }
}
