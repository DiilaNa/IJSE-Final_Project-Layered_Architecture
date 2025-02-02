package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.EmpSchedule;

import java.sql.SQLException;

public interface EmpScheduleDAO extends CrudDAO<EmpSchedule> {
    String searchEmID(String emID) throws SQLException, ClassNotFoundException;
}
