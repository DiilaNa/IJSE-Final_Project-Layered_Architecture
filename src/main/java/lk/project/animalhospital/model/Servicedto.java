package lk.project.animalhospital.model;

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
