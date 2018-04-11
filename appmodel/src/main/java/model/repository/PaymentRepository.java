package model.repository;

import model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by psawz on 19.04.2017.
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAll();
    Payment findByIdPayment(Integer id);
    List<Payment> findByDate(Date date);
}
