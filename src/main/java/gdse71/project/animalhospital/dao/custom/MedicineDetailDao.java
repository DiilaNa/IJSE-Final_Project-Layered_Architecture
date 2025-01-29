package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.MedicineDetails;

import java.sql.SQLException;

public interface MedicineDetailDao extends CrudDAO<MedicineDetails> {
    String searchPetID(String petID) throws SQLException, ClassNotFoundException;

}
