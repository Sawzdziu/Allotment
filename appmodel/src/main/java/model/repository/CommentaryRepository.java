package model.repository;

import model.entity.Commentary;
import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by psawz on 09.05.2017.
 */
public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {

    Commentary findByIdCommentary(Integer id);
    Optional<List<Commentary>> findByUser(User user);
    Optional<List<Commentary>> findAllByArticleIdArticle(Integer id);
}
