package lk.project.animalhospital.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PaymentDto {
    private String paymentId;
    private String paymentDate;
    private String paymentMethodd;
    private String paymentTime;
}
