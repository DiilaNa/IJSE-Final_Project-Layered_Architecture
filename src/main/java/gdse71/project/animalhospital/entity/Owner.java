package gdse71.project.animalhospital.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Owner {
    private String OwnerId;
    private String OwnerName;
    private String OwnerAddress;
    private String OwnerMail;
}
