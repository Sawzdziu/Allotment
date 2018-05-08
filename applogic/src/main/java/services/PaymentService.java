package services;

import dto.payment.AddPaymentDto;
import dto.payment.EditPaymentDto;
import dto.payment.PaymentDto;
import model.dao.AllotmentRepositoryDAO;
import model.dao.AllotmentUserRepositoryDAO;
import model.dao.PaymentRepositoryDAO;
import model.dao.UserRepositoryDAO;
import model.entity.Allotment;
import model.entity.AllotmentUser;
import model.entity.Payment;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepositoryDAO paymentRepositoryDAO;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Autowired
    private AllotmentRepositoryDAO allotmentRepositoryDAO;

    @Autowired
    private AllotmentUserRepositoryDAO allotmentUserRepositoryDAO;

    public List<PaymentDto> getAllPayments(){
        return mapToPaymentDto(paymentRepositoryDAO.getAll());
    }

    public List<PaymentDto> getPayments(){
        return mapToPaymentDto(paymentRepositoryDAO.getPaymentForUser(getUser()));
    }

    public void createPayment(AddPaymentDto addPaymentDto){
        Payment payment = new Payment();
        payment.setCharge(addPaymentDto.getCharge());
        payment.setTitle(addPaymentDto.getTitle());
        payment.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        payment.setPaid(false);
        payment.setAllotmentUserByAllotmentUserId(getAllotmentUser(addPaymentDto.getUserId()));

        persistPayment(payment);
    }

    public void confirmPayment(Integer id){
        Payment payment = paymentRepositoryDAO.getByPaymentId(id);
        payment.setPaid(true);

        persistPayment(payment);
    }

    public void updatePayment(EditPaymentDto editPaymentDto){
        Payment payment = paymentRepositoryDAO.getByPaymentId(editPaymentDto.getIdPayment());
        payment.setTitle(editPaymentDto.getTitle());
        payment.setCharge(editPaymentDto.getCharge());

        persistPayment(payment);
    }

    public void deletePayment(Integer id){
        paymentRepositoryDAO.delete(id);
    }

    private User getUserById(Integer idUser){
        return userRepositoryDAO.findById(idUser);
    }

    private Allotment getAllotmentById(Integer idAllotment){
        return allotmentRepositoryDAO.getAllotmentById(idAllotment);
    }

    private void persistPayment(Payment payment){
        paymentRepositoryDAO.save(payment);
    }

    private AllotmentUser getAllotmentUser(Integer idUser){
        return allotmentUserRepositoryDAO.findAllotmentUserByUserAndActiveTrue(userRepositoryDAO.findById(idUser));
    }

    private List<PaymentDto> mapToPaymentDto(List<Payment> payments){
        return payments.stream().map(PaymentDto::new).collect(Collectors.toList());
    }

    private User getUser(){
        return authenticationService.getUser();
    }
}
