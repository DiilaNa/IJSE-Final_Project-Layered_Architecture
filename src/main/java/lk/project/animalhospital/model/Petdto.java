package lk.project.animalhospital.model;

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
