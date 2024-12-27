package gdse71.project.animalhospital.dto.PetTm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ServiceTM {
    private String ServiceID;
    private String ServiceName;
    private String Duration;
    private String PetIdService;
}
