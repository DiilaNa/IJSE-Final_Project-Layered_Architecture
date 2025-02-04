package lk.project.animalhospital.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ViewAppointments {
    private String appointmentId;
    private String Date;
    private String Time;
    private String petId;
    private String payID;
    private String Status;

}
