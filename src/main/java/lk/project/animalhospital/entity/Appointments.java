package lk.project.animalhospital.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Appointments {
    private String AptID;
    private String AptDate;
    private String AptTime;
    private String PayID;
    private String Pet_ID;


}