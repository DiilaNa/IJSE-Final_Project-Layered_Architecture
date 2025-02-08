package lk.project.animalhospital.model;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ScheduleDto {
    private String ScheduleID;
    private String Date;
    private String Time;
}
