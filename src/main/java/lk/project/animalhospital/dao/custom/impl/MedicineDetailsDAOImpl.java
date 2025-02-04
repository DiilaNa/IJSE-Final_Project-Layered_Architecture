package lk.project.animalhospital.dao.custom.impl;

import lk.project.animalhospital.CrudUtil.Util;
import lk.project.animalhospital.dao.custom.MedicineDetailDao;
import lk.project.animalhospital.entity.MedicineDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDetailsDAOImpl implements MedicineDetailDao {
    @Override
    public ArrayList<MedicineDetails> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(MedicineDetails entity) throws Exception {
        return Util.execute("INSERT INTO medicine_details VALUES (?,?)",
                entity.getMeDID(),
                entity.getPETID()
                );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return Util.execute("DELETE FROM medicine_details WHERE pet_id = ?",id);

    }

    @Override
    public boolean update(MedicineDetails entity) throws Exception {
        return false;
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }

    @Override
    public String searchPetID(String ID) throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT pet_id FROM medicine_details WHERE med_id = ?",ID);
        if(rst.next()){
            return rst.getString("pet_id");
        }
        return "NO ID FOUND";
    }
}
