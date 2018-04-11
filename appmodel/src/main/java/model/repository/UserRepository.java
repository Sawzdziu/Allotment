package model.repository;

import model.entity.Role;
import model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by psawz on 19.04.2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findByIdUser(Integer id);
    Optional<List<User>> findByRole(Role role);
    Optional<List<User>> findByLastName(String lastName);
    Optional<User> findByEmail(String email);
    Optional<List<User>> findByActiveTrue();
}
