package gdse71.project.animalhospital.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Servicedto {
        private String ServiceID;
        private String ServiceName;
        private String Duration;
        private String PetIdService;
}
