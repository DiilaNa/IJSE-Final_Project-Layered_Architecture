package gdse71.project.animalhospital.dto;

import lombok.*;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString

    public class ViewAppointmentDto {
        private String appointmentId;
        private String Date;
        private String Time;
        private String petId;
        private String payID;
        private String Status;

    }
