package model.repository;

import model.entity.Payment;
import model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by psawz on 19.04.2017.
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAll();
    Optional<Payment> findByIdPayment(Integer id);
    Optional<List<Payment>> findByDate(Date date);
    Optional<List<Payment>> findAllByAllotmentUserByAllotmentUserId_User(User user);
}
