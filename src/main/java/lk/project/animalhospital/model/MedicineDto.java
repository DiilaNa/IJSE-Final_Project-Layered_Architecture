package lk.project.animalhospital.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class MedicineDto {
    private String MedicineId;
    private String MedicineName;
    private String MedicineCondition;
    private Double MedicineWeight;
}
