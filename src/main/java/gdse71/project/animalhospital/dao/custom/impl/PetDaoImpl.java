package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.PetDao;
import gdse71.project.animalhospital.entity.Pet;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDaoImpl implements PetDao {

    @Override
    public ArrayList<Pet> getAll() throws Exception, ClassNotFoundException {
       ResultSet rst = Util.execute("select * from pet");

       ArrayList<Pet> petDTOS = new ArrayList<>();
       while (rst.next()){
           petDTOS.add(new Pet(
                   rst.getString(1),
                   rst.getString(2),
                   rst.getString(3),
                   rst.getString(4),
                   rst.getString(5)

           ));
       }
       return petDTOS;
    }

    @Override
    public boolean save(Pet dto) throws Exception, ClassNotFoundException {
        return false;
    }
    @Override
    public boolean delete(String petId) throws Exception, ClassNotFoundException {
        try {
            return Util.execute("delete from pet where pet_id=?", petId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Pet entity) throws Exception, ClassNotFoundException {
        try {
            return Util.execute(
                    "update pet set pet_name=?, breed=?, ownerid=? where pet_id=?",
                    entity.getPetName(),
                    entity.getPetBreed(),
                    entity.getPetOwnerId(),
                    entity.getPetId()
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

    @Override
    public String generateId() throws Exception, ClassNotFoundException {
        return "";
    }
}
