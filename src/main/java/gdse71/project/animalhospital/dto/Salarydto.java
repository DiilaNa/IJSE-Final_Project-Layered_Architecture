package gdse71.project.animalhospital.dto;

import lombok.*;

import java.util.Date;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Salarydto {
    private String salaryId;
    private Date date;
    private Double amount;
    private String EmployeeId;
}


