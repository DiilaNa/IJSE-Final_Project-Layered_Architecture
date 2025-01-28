package gdse71.project.animalhospital.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Sms {
    private String smsNo;
    private String date;
    private String status;
    private String appID;
}
