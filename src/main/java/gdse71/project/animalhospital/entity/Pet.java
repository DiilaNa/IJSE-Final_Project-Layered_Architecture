package gdse71.project.animalhospital.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Pet {
    private String petId;
    private String petName;
    private String petBreed;
    private String petOwnerId;
    private String PetType;
}
