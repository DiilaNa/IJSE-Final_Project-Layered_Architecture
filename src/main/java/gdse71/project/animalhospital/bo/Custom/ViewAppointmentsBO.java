package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.ViewAppointmentDto;
import gdse71.project.animalhospital.entity.ViewAppointments;

import java.util.ArrayList;

public interface ViewAppointmentsBO extends SuperBO {
     ArrayList<ViewAppointmentDto> getAllAppointments() throws Exception;
}
