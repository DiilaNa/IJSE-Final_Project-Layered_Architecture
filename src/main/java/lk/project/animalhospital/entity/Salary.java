package lk.project.animalhospital.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Salary {
    private String salaryId;
    private Date date;
    private Double amount;
    private String EmployeeId;
}
