package gdse71.project.animalhospital.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Petdto {
    private String petId;
    private String petName;
    private String petBreed;
    private String petOwnerId;
    private String PetType;
}
