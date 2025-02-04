package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.MedicineDetails;

import java.sql.SQLException;

public interface MedicineDetailDao extends CrudDAO<MedicineDetails> {
    String searchPetID(String petID) throws SQLException, ClassNotFoundException;

}
