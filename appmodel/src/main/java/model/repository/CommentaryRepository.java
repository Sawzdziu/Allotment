package model.repository;

import model.entity.Commentary;
import model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by psawz on 09.05.2017.
 */
public interface CommentaryRepository extends CrudRepository<Commentary, Integer>{

    Commentary findByIdCommentary(Integer id);
    List<Commentary> findByUser(User user);
}
