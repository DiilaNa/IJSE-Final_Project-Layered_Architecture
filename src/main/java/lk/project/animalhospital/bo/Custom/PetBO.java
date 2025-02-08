package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.model.Petdto;

import java.util.ArrayList;

public interface PetBO extends SuperBO {
    ArrayList<Petdto> getAllPets() throws Exception;
    boolean deletePets(String petid);
    boolean updatePets(Petdto pet) throws Exception;
}
