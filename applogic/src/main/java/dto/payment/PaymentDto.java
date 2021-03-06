package dto.payment;

import dto.allotmentUser.AllotmentDto;
import dto.allotmentUser.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Payment;

@Data
@NoArgsConstructor
public class PaymentDto {

    private Integer idPayment;
    private Double charge;
    private Boolean paid;
    private String title;
    private AllotmentDto allotmentDto;
    private UserDto userDto;

    public PaymentDto(Payment payment) {
        this.idPayment = payment.getIdPayment();
        this.charge = payment.getCharge();
        this.paid = payment.getPaid();
        this.title = payment.getTitle();
        this.allotmentDto = new AllotmentDto(payment.getAllotmentUserByAllotmentUserId().getAllotment());
        this.userDto = new UserDto(payment.getAllotmentUserByAllotmentUserId().getUser());
    }
}
