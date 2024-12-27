package gdse71.project.animalhospital.dto.PetTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class MedicineTM {
    private String MedicineId;
    private String MedicineName;
    private String MedicineCondition;
    private Double MedicineWeight;

}
