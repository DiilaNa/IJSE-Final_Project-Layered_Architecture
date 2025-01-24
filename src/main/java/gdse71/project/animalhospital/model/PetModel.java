package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.Petdto;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetModel {

    public ArrayList<Petdto> getALLPET() throws SQLException, ClassNotFoundException {
        ResultSet rst =  Util.execute("select * from pet");

        ArrayList<Petdto> petDTOS = new ArrayList<>();

        while (rst.next()){
            Petdto petdto = new Petdto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            petDTOS.add(petdto);
        }
        return petDTOS;
    }
    public boolean updatePet(Petdto petdto) {
        try {
            return Util.execute(
                    "update pet set pet_name=?, breed=?, ownerid=? where pet_id=?",
                    petdto.getPetName(),
                    petdto.getPetBreed(),
                    petdto.getPetOwnerId(),
                    petdto.getPetId()
                    );
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Syntax Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Check your SQL Querry");
            alert.showAndWait();
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deletePet(String petId)  {
        try {
            return Util.execute("delete from pet where pet_id=?", petId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
