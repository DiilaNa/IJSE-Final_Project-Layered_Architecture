package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.EmpSchedule;

import java.sql.SQLException;

public interface EmpScheduleDAO extends CrudDAO<EmpSchedule> {
    String searchEmID(String emID) throws SQLException, ClassNotFoundException;
}
