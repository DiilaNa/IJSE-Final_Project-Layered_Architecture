package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Petdto;

import java.util.ArrayList;

public interface PetBO extends SuperBO {
    ArrayList<Petdto> getAllPets() throws Exception;
    boolean deletePets(String petid);
    boolean updatePets(Petdto pet) throws Exception;
}
