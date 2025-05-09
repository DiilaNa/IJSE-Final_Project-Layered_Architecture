package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.model.Appointmentsdto;
import lk.project.animalhospital.model.Ownerdto;
import lk.project.animalhospital.model.PaymentDto;
import lk.project.animalhospital.model.Petdto;

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
