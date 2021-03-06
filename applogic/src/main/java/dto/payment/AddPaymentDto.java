package dto.payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddPaymentDto {

    private Double charge;
    private String title;
    private Integer userId;
}
