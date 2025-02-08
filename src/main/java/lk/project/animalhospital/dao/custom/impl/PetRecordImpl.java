package lk.project.animalhospital.dao.custom.impl;

import lk.project.animalhospital.dao.Util;
import lk.project.animalhospital.dao.custom.PetRecordDao;
import lk.project.animalhospital.entity.PetRecord;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PetRecordImpl implements PetRecordDao {

    @Override
    public ArrayList<PetRecord> getAll() throws Exception, ClassNotFoundException {
        ResultSet rst =  Util.execute("select * from records");

        ArrayList<PetRecord> petRecorddtos = new ArrayList<>();

        while (rst.next()){
            petRecorddtos.add(new PetRecord(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)

            ));
        }
        return petRecorddtos;
    }
    @Override
    public boolean save(PetRecord entity) throws Exception, ClassNotFoundException {
        return Util.execute(
                "insert into records values (?,?,?,?,?)",
                entity.getRecordId(),
                entity.getStatus(),
                entity.getDescription(),
                entity.getWeight(),
                entity.getPetID()
        );
    }

    @Override
    public boolean delete(String id) throws Exception, ClassNotFoundException {
        return Util.execute("delete from records where rec_id=?", id);
    }

    @Override
    public boolean update(PetRecord entity) throws Exception, ClassNotFoundException {
        return Util.execute(
                "update records set  status=?, description=?, weight=? , petid=?  where rec_id=?",
                entity.getStatus(),
                entity.getDescription(),
                entity.getWeight(),
                entity.getPetID(),
                entity.getRecordId()
        );
    }

    @Override
    public String generateId() throws Exception, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT rec_id FROM records order by rec_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String numericPart = lastId.replaceAll("[^0-9]", "");
            if (numericPart.isEmpty()){
                return "REC001";
            }
            int i = Integer.parseInt(numericPart);
            int newIndex = i+1;
            return String.format("REC%03d",newIndex);
        }else {
            return "REC001";
        }
    }
    @Override
    public ArrayList<String> loadId() throws Exception {
        ResultSet rst = Util.execute("SELECT pet_id FROM pet");
        ArrayList<String> petIds = new ArrayList<>();

        while (rst.next()) {
            petIds.add(rst.getString("pet_id"));
        }

        return petIds;
    }

}
