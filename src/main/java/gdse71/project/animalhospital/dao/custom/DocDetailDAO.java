package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.DocDetails;

import java.sql.SQLException;

public interface DocDetailDAO extends CrudDAO<DocDetails> {
    String searchAppointmentID(String appointmentID) throws SQLException, ClassNotFoundException;

}
