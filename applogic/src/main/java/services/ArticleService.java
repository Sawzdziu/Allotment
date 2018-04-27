package services;

import dto.ArticleDto;
import model.dao.ArticleRepositoryDAO;
import model.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepositoryDAO articleRepositoryDAO;

    public List<ArticleDto> getAllArticles(){
        return mapToArticleDto(articleRepositoryDAO.getAll());
    }

    public List<ArticleDto> getLastFiveArticles(){
        return mapToArticleDto(articleRepositoryDAO.getLastFiveArticles());
    }

    public void createNewArticle(ArticleDto articleDto){

    }

    private List<ArticleDto> mapToArticleDto(List<Article> articleList){
        return articleList.stream().map(ArticleDto::new).collect(Collectors.toList());
    }
}
