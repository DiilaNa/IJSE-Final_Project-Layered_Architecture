package lk.project.animalhospital.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Ownerdto {
    private String OwnerId;
    private String OwnerName;
    private String OwnerAddress;
    private String OwnerMail;
}
