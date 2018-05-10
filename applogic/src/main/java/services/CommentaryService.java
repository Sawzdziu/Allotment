package services;

import dto.article.CommentaryDto;
import model.dao.ArticleRepositoryDAO;
import model.dao.CommentaryRepositoryDAO;
import model.entity.Article;
import model.entity.Commentary;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentaryService {

    @Autowired
    private ArticleRepositoryDAO articleRepositoryDAO;

    @Autowired
    private CommentaryRepositoryDAO commentaryRepositoryDAO;

    @Autowired
    private AuthenticationService authenticationService;

    public List<CommentaryDto> getCommentariesFromArticle(Integer id) {
        return mapToCommentaryDto(commentaryRepositoryDAO.getAllCommentariesForArticleId(id));
    }

    public void addCommentary(CommentaryDto commentaryDto) {
        Commentary commentary = new Commentary();
        commentary.setArticle(getArticle(commentaryDto.getIdArticle()));
        commentary.setText(commentaryDto.getText());

        User user = authenticationService.getUser();

        commentary.setUser(user);
        commentary.setAuthor(getAuthor(user));
        commentary.setDate(new Date());

        persistCommentary(commentary);
    }

    public void editCommentary(CommentaryDto commentaryDto) {
        Commentary commentary = commentaryRepositoryDAO.getCommentaryById(commentaryDto.getIdCommentary());
        if (commentary.getUser().equals(authenticationService.getUser())) {
            commentary.setDate(new Date());
            commentary.setText(commentaryDto.getText());

            persistCommentary(commentary);
        } else {
            throw new AccessDeniedException("You can't modify someone else commentary!");
        }
    }

    public void deleteCommentary(Integer id) {
        Commentary commentary = commentaryRepositoryDAO.getCommentaryById(id);
        if (commentary.getUser().equals(authenticationService.getUser())) {
            commentaryRepositoryDAO.delete(commentary);
        } else {
            throw new AccessDeniedException("You can't modify someone else commentary!");
        }
    }

    private void persistCommentary(Commentary commentary) {
        commentaryRepositoryDAO.save(commentary);
    }

    private Article getArticle(Integer id) {
        return articleRepositoryDAO.getArticlesById(id);
    }

    private String getAuthor(User user) {
        return user.getName() + " " + user.getLastName();
    }

    private List<CommentaryDto> mapToCommentaryDto(List<Commentary> articleList) {
        return articleList.stream().map(CommentaryDto::new).collect(Collectors.toList());
    }
}
