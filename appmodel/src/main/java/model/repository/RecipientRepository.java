package model.repository;

import model.entity.Recipient;
import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by piotrsa on 25/04/18.
 */
public interface RecipientRepository extends JpaRepository<Recipient, Integer> {

    Optional<List<Recipient>> findAllByUserReciever(User user);
    Optional<List<Recipient>> findFirst5ByUserRecieverOrderByIdRecipientDesc(User user);
}
