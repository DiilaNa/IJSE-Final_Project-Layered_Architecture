package gdse71.project.animalhospital.dto.PetTm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SalaryTM {
    private String salaryId;
    private Date date;
    private Double amount;
    private String EmployeeId;
}
