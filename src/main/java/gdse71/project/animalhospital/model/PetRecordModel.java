package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.Ownerdto;
import gdse71.project.animalhospital.dto.PetRecorddto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetRecordModel {
    public ArrayList<PetRecorddto> getAllpetRec() throws SQLException, ClassNotFoundException {
        ResultSet rst =  Util.execute("select * from records");

        ArrayList<PetRecorddto> petRecorddtos = new ArrayList<>();

        while (rst.next()){
            PetRecorddto petRecorddto = new PetRecorddto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)

            );
            petRecorddtos.add(petRecorddto);
        }
        return petRecorddtos;
    }
    public boolean savePetRec(PetRecorddto petRecorddto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "insert into records values (?,?,?,?,?)",
                petRecorddto.getRecordId(),
                petRecorddto.getStatus(),
                petRecorddto.getDescription(),
                petRecorddto.getWeight(),
                petRecorddto.getPetID()
        );
    }
    public boolean updatePetRec(PetRecorddto petRecorddto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "update records set  status=?, description=?, weight=? , petid=?  where rec_id=?",
                petRecorddto.getStatus(),
                petRecorddto.getDescription(),
                petRecorddto.getWeight(),
                petRecorddto.getPetID(),
                petRecorddto.getRecordId()
                );
    }
    public boolean deletePetRec(String rec_id) throws SQLException, ClassNotFoundException {
        return Util.execute("delete from records where rec_id=?", rec_id);
    }


}
