package gdse71.project.animalhospital.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Service {
    private String ServiceID;
    private String ServiceName;
    private String Duration;
    private String PetIdService;
}