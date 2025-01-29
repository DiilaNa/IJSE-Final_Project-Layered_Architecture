package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.MedicineDetailDao;
import gdse71.project.animalhospital.entity.MedicineDetails;

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
        System.out.println("pet_id in medicineDetailsDAOImpl" + id);
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
