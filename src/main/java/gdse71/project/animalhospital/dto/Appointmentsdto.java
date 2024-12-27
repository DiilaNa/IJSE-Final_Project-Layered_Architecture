package gdse71.project.animalhospital.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

    public class Appointmentsdto {
    private String AptID;
    private String AptDate;
    private String AptTime;
    private String PayID;
    private String Pet_ID;


}


