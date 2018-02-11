package model.dao;

import model.entity.Article;
import model.entity.User;
import model.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by psawz on 09.05.2017.
 */
@Repository
public class ArticlesRepositoryDAO {

    @Autowired
    private ArticleRepository articlesRepository;

    public Article getArticlesById(Integer id) {
        return articlesRepository.findByIdArticle(id);
    }

    public List<Article> getAll() {
        return articlesRepository.findAll();
    }

    public List<Article> getArticlesByUser(User user) {
        return articlesRepository.findByUser(user);
    }

    public List<Article> getArticlesByDate(Date date) {
        return articlesRepository.findByDate(date);
    }

    public void save(Article articles) {
        articlesRepository.save(articles);
    }
}
