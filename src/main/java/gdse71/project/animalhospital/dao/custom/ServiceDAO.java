package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceDAO extends CrudDAO<Service> {
    ArrayList<String> loadPetID() throws SQLException, ClassNotFoundException;
}
