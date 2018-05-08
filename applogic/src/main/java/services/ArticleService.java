package services;

import dto.article.ArticleDto;
import dto.article.CommentaryDto;
import model.dao.ArticleRepositoryDAO;
import model.dao.UserRepositoryDAO;
import model.entity.Article;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepositoryDAO articleRepositoryDAO;

    @Autowired
    private CommentaryService commentaryService;

    @Autowired
    private AuthenticationService authenticationService;

    public List<ArticleDto> getAllArticles() {
        return setCommentaries(mapToArticleDto(sort(articleRepositoryDAO.getAll())));
    }

    public List<ArticleDto> getLastFiveArticles() {
        return mapToArticleDto(sort(articleRepositoryDAO.getLastFiveArticles()));
    }

    public void createNewArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setText(articleDto.getText());
        article.setTitle(articleDto.getTitle());
        article.setDate(new Date());

        User user = getUser();

        article.setUser(user);
        article.setAuthor(getAuthor(user));

        persistArticle(article);
    }

    public void editArticle(ArticleDto articleDto) {
        Article article = articleRepositoryDAO.getArticlesById(articleDto.getIdArticle());
        if (article.getUser().equals(getUser())) {
            article.setDate(new Date());
            article.setTitle(articleDto.getTitle());
            article.setText(articleDto.getText());

            persistArticle(article);
        } else {
            throw new AccessDeniedException("You can't modify someone else article!");
        }
    }

    public void deleteArticle(Integer id) {
        Article article = articleRepositoryDAO.getArticlesById(id);
        if (article.getUser().equals(getUser())) {
            articleRepositoryDAO.delete(article);
        } else {
            throw new AccessDeniedException("You can't modify someone else article!");
        }
    }

    private List<ArticleDto> setCommentaries(List<ArticleDto> articleDtoList) {
        articleDtoList.forEach(articleDto -> articleDto.setCommentaryDtoList(getCommentariesForArticle(articleDto.getIdArticle())));
        return articleDtoList;
    }

    private List<CommentaryDto> getCommentariesForArticle(Integer idArticle) {
        return commentaryService.getCommentariesFromArticle(idArticle);
    }

    private List<Article> sort(List<Article> articleList) {
        List<Article> sortedArticles = articleList.stream()
                .sorted(Comparator.comparing(Article::getDate).reversed())
                .collect(Collectors.toList());
        return sortedArticles;
    }

    private String getAuthor(User user) {
        return user.getName() + " " + user.getLastName();
    }

    private void persistArticle(Article article) {
        articleRepositoryDAO.save(article);
    }

    private User getUser() {
        return authenticationService.getUser();
    }

    private List<ArticleDto> mapToArticleDto(List<Article> articleList) {
        return articleList.stream().map(ArticleDto::new).collect(Collectors.toList());
    }
}
