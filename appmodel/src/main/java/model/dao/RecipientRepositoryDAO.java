package model.dao;

import model.entity.Recipient;
import model.entity.User;
import model.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by piotrsa on 25/04/18.
 */
@Repository
public class RecipientRepositoryDAO {

    @Autowired
    private RecipientRepository recipientRepository;

    @Transactional
    public List<Recipient> findAllMailsForUser(User user){
        return recipientRepository.findAllByUserReciever(user).orElseThrow(() -> new NoResultException());
    }

    @Transactional
    public List<Recipient> findLastFiveMailsForUser(User user){
        return recipientRepository.findFirst5ByUserRecieverOrderByIdRecipientDesc(user).orElseThrow(() -> new NoResultException());
    }
}
