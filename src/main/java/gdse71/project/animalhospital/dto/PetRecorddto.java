package gdse71.project.animalhospital.dto;

import lombok.*;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString

    public class PetRecorddto {
        private String recordId;
        private String status;
        private String description;
        private double weight;
        private String petID;
    }
