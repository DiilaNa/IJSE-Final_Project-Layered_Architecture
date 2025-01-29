package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineDAO extends CrudDAO<Medicine> {
    ArrayList<String> loadPetID() throws SQLException, ClassNotFoundException;
    String search(String petID) throws SQLException, ClassNotFoundException;
}
