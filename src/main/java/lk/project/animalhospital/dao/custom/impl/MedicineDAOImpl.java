package lk.project.animalhospital.dao.custom.impl;

import lk.project.animalhospital.CrudUtil.Util;
import lk.project.animalhospital.dao.custom.MedicineDAO;
import lk.project.animalhospital.entity.Medicine;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public ArrayList<Medicine> getAll() throws Exception {
        ResultSet rst = Util.execute("select * from medicine");
        ArrayList<Medicine> medicines = new ArrayList<>();
        while (rst.next()) {
            medicines.add(new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            ));
        }
        return medicines;
    }

    @Override
    public boolean save(Medicine entity) throws Exception {
        return Util.execute("INSERT INTO medicine VALUES (?,?,?,?)",
                entity.getMedicineId(),
                entity.getMedicineName(),
                entity.getMedicineCondition(),
                entity.getMedicineWeight()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return Util.execute("DELETE FROM medicine WHERE medicine_id=?",id);
    }

    @Override
    public boolean update(Medicine entity) throws Exception {
        return Util.execute("UPDATE medicine SET med_name=?,med_condition=?,weight=? WHERE medicine_id=?",
                entity.getMedicineName(),
                entity.getMedicineCondition(),
                entity.getMedicineWeight(),
                entity.getMedicineId()
        );
    }

    @Override
    public String generateId() throws Exception {
       ResultSet rst = Util.execute("SELECT medicine_id FROM medicine order by medicine_id desc limit 1");
       if (rst.next()) {
           String lastId = rst.getString(1);
           String numericPart = lastId.replaceAll("[^0-9]", "");
           if (numericPart.isEmpty()){
               return "MED001";
           }
           int i = Integer.parseInt(numericPart);
           int newIndex = i+1;
           return String.format("MED%03d",newIndex);
       }else {
           return "MED001";
       }
    }

    @Override
    public ArrayList<String> loadPetID() throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT pet_id FROM pet");
        ArrayList<String> petIds = new ArrayList<>();

        while (rst.next()) {
            petIds.add(rst.getString("pet_id"));
        }

        return petIds;
    }
    @Override
    public String searchPetName(String petID) throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("select pet_name from pet where pet_id = ?",petID);

        if (rst.next()) {
            return rst.getString("pet_name");
        }
        return "No Name Found";
    }
}
