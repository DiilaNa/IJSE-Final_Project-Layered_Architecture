package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.dto.ViewAppointmentDto;

import java.util.ArrayList;

public interface ViewAppointmentsBO extends SuperBO {
     ArrayList<ViewAppointmentDto> getAllAppointments() throws Exception;
     boolean deleteAppointment(String Appointment_id) throws Exception;
}
