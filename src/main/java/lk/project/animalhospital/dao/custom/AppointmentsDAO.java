package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.Appointments;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentsDAO extends CrudDAO<Appointments> {
     String getNextAppointmentID();
     String getNextPetID();
     String getNextOwnerID();
     String getNextPayID();
     boolean CancelApt(String id) throws SQLException, ClassNotFoundException;
     ArrayList<String> LoadId() throws SQLException, ClassNotFoundException;
}
