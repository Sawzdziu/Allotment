package model.dao;

import model.entity.Payment;
import model.entity.User;
import model.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return paymentRepository.findByIdPayment(id);
    }

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentByDate(Date date){
        return  paymentRepository.findByDate(date);
    }

    public void save(Payment payment){
        paymentRepository.save(payment);
    }

}
