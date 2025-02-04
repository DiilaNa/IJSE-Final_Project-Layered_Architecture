package lk.project.animalhospital.entity;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Payment {
    private String paymentId;
    private String paymentDate;
    private String paymentMethodd;
    private String paymentTime;
}
