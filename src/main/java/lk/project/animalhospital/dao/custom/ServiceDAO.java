package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceDAO extends CrudDAO<Service> {
    ArrayList<String> loadPetID() throws SQLException, ClassNotFoundException;
}
