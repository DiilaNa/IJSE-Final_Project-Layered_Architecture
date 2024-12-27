package gdse71.project.animalhospital.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Smsdto {
    private String smsNo;
    private String date;
    private String status;
    private String appID;
}
