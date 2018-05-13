package model.repository;

import model.entity.Allotment;
import model.entity.AllotmentUser;
import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AllotmentUserRepository extends JpaRepository<AllotmentUser, Integer> {

    Optional<List<AllotmentUser>> findAllByUserIn(List<User> users);
    Optional<List<AllotmentUser>> findAllByActiveTrue();
    Optional<List<AllotmentUser>> findAllByAllotmentAndActiveFalse(Allotment allotment);
    Optional<List<AllotmentUser>> findByUser(User user);
    Optional<AllotmentUser> findByUserAndActiveTrue(User user);
    Optional<AllotmentUser> findByUserAndAllotment(User user, Allotment allotment);
    Optional<List<AllotmentUser>> findByAllotment(Allotment allotment);
}
