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

    /**
     * @return List of all articles and commentaries
     */
    public List<ArticleDto> getAllArticles() {
        return setCommentaries(mapToArticleDto(sort(articleRepositoryDAO.getAll())));
    }

    /**
     * @return Last five articles and its commentaries from application
     */
    public List<ArticleDto> getLastFiveArticles() {
        return mapToArticleDto(sort(articleRepositoryDAO.getLastFiveArticles()));
    }

    /**
     * Method creates new Article based on data from articleDto
     *
     * @param articleDto containing data needed  to create new Article
     */
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

    /**
     * Method update article based on data from articleDto
     *
     * @param articleDto containing data needed to update new Article
     */
    public void editArticle(ArticleDto articleDto) {
        Article article = articleRepositoryDAO.getArticlesById(articleDto.getIdArticle());
        if (article.getUser().equals(getUser())) {
            article.setDate(new Date());
            article.setTitle(articleDto.getTitle());
            article.setText(articleDto.getText());
            article.setAuthor(getAuthor(getUser()));

            persistArticle(article);
        } else {
            throw new AccessDeniedException("You can't modify someone else article!");
        }
    }

    /**
     * Deletes Article specified by id
     *
     * @param id of article
     */
    public void deleteArticle(Integer id) {
        Article article = articleRepositoryDAO.getArticlesById(id);
        if (article.getUser().equals(getUser())) {
            articleRepositoryDAO.delete(article);
        } else {
            throw new AccessDeniedException("You can't modify someone else article!");
        }
    }

    /**
     * @param articleDtoList list of articles
     * @return List of mapped article with commentaries
     */
    private List<ArticleDto> setCommentaries(List<ArticleDto> articleDtoList) {
        articleDtoList.forEach(articleDto -> articleDto.setCommentaryDtoList(getCommentariesForArticle(articleDto.getIdArticle())));
        return articleDtoList;
    }

    /**
     * @param idArticle of article
     * @return List of commentaries mapped to dto object for specified article
     */
    private List<CommentaryDto> getCommentariesForArticle(Integer idArticle) {
        return commentaryService.getCommentariesFromArticle(idArticle);
    }

    /**
     * @param articleList list of articles
     * @return List of articles sorted by its creation date
     */
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
