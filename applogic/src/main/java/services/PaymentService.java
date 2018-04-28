package services;

import dto.PaymentDto;
import model.dao.PaymentRepositoryDAO;
import model.entity.Payment;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepositoryDAO paymentRepositoryDAO;

    @Autowired
    private AuthenticationService authenticationService;

    public List<PaymentDto> getAllPayments(){
        return mapToPaymentDto(paymentRepositoryDAO.getAll());
    }

    public List<PaymentDto> getPayments(){
        return mapToPaymentDto(paymentRepositoryDAO.getPaymentForUser(getUser()));
    }

    private List<PaymentDto> mapToPaymentDto(List<Payment> payments){
        return payments.stream().map(PaymentDto::new).collect(Collectors.toList());
    }

    private User getUser(){
        return authenticationService.getUser();
    }
}
