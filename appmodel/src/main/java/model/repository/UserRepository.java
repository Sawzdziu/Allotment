package model.repository;

import model.entity.Role;
import model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by psawz on 19.04.2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
    User findByIdUser(Integer id);
    List<User> findByRole(Role role);
    List<User> findBySurname(String surname);
    User findByEmail(String email);
}
