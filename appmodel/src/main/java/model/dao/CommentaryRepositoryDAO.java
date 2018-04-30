package model.dao;

import model.entity.Commentary;
import model.entity.User;
import model.repository.CommentaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by psawz on 09.05.2017.
 */
@Repository
public class CommentaryRepositoryDAO {

    @Autowired
    private CommentaryRepository commentaryRepository;

    public Commentary getCommentaryById(Integer id){
        return commentaryRepository.findByIdCommentary(id);
    }

    public List<Commentary> getCommentariesByUser(User user){
        return commentaryRepository.findByUser(user).orElseThrow(NoResultException::new);
    }

    public List<Commentary> getAllCommentariesForArticleId(Integer id){
        return commentaryRepository.findAllByArticleIdArticle(id).orElseThrow(NoResultException::new);
    }

    public void save(Commentary commentary){
        commentaryRepository.save(commentary);
    }
}
