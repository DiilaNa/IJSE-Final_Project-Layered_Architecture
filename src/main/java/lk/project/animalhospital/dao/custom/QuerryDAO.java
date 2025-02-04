package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.SuperDAO;

import java.sql.SQLException;

public interface QuerryDAO extends SuperDAO {
     boolean delete(String appointment_id) throws SQLException, ClassNotFoundException;
}
