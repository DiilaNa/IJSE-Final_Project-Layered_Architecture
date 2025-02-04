package lk.project.animalhospital.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
    private String EmployeeId;
    private String EmployeeName;
    private String EmployeeDuty;
    private String EmployeeAddress;
    private String EmployeePhone;
}
