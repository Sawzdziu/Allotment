package model.dao;

import model.entity.Role;
import model.entity.User;
import model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by psawz on 22.04.2017.
 */
@Repository
public class UserRepositoryDAO {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role).orElseThrow(() -> new NullPointerException());
    }

    @Transactional
    public User findById(Integer id) throws Exception {
        return userRepository.findByIdUser(id).orElseThrow(() -> new NullPointerException());
    }

    @Transactional
    public List<User> findBySurame(String surname) {
        return userRepository.findBySurname(surname).orElseThrow(() -> new NullPointerException());
    }

    @Transactional
    public User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email).orElseThrow((() -> new NullPointerException()));
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
