package gdse71.project.animalhospital.dto.PetTm;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ApointmentsTM {
    private String AptID;
    private String AptDate;
    private String AptTime;
    private String PayID;

}
