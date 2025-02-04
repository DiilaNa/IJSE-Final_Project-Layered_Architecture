package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineDAO extends CrudDAO<Medicine> {
    ArrayList<String> loadPetID() throws SQLException, ClassNotFoundException;
    String searchPetName(String petID) throws SQLException, ClassNotFoundException;
}
