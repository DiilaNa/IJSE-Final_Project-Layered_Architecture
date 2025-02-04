package lk.project.animalhospital.entity;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PetRecord {
    private String recordId;
    private String status;
    private String description;
    private double weight;
    private String petID;
}

