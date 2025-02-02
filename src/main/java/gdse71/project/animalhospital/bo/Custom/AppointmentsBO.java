package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Appointmentsdto;
import gdse71.project.animalhospital.dto.Ownerdto;
import gdse71.project.animalhospital.dto.PaymentDto;
import gdse71.project.animalhospital.dto.Petdto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AppointmentsBO extends SuperBO {
    String getNextAppointmentID();
    String getNextPetID();
    String getNextOwnerID();
    String getNextPayID();
    boolean CancelApt(String AppointmentSid) throws SQLException, ClassNotFoundException;
    boolean saveAppointment(List<Ownerdto>ownerdtos,List<Petdto>petdtos,List<PaymentDto>paymentdtos,List<Appointmentsdto> appointmentsdtos) throws SQLException;
    public ArrayList<String> LoadAppointmentsId() throws SQLException, ClassNotFoundException;
}
