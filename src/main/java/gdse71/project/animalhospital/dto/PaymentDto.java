package gdse71.project.animalhospital.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
