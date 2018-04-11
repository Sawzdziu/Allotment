package model.repository;

import model.entity.Commentary;
import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by psawz on 09.05.2017.
 */
public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {

    Commentary findByIdCommentary(Integer id);
    List<Commentary> findByUser(User user);
}
