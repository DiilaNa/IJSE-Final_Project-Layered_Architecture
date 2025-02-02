package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.Appointments;

import java.sql.SQLException;

public interface AppointmentsDAO extends CrudDAO<Appointments> {
     String getNextAppointmentID();
     String getNextPetID();
     String getNextOwnerID();
     String getNextPayID();
     boolean CancelApt(String id) throws SQLException, ClassNotFoundException;
}
