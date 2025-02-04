package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.DocDetails;

import java.sql.SQLException;

public interface DocDetailDAO extends CrudDAO<DocDetails> {
    String searchAppointmentID(String appointmentID) throws SQLException, ClassNotFoundException;

}
