package gdse71.project.animalhospital.dto.PetTm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PaymentTM {
    private String paymentId;
    private String paymentDate;
    private String paymentMethodd;
    private String paymentTime;
}
