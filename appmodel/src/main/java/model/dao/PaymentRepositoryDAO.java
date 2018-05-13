package model.dao;

import model.entity.Payment;
import model.entity.User;
import model.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 * Created by psawz on 09.05.2017.
 */
@Repository
public class PaymentRepositoryDAO {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getByPaymentId(Integer id) {
        return paymentRepository.findByIdPayment(id).orElseThrow(NoResultException::new);
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentByDate(Date date) {
        return paymentRepository.findByDate(date).orElseThrow(NoResultException::new);
    }

    public List<Payment> getPaymentForUser(User user) {
        return paymentRepository.findAllByAllotmentUserByAllotmentUserId_User(user).orElseThrow(NoResultException::new);
    }

    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    public void delete(Integer id) {
        paymentRepository.delete(id);
    }

}
