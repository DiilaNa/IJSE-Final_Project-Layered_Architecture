package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Petdto;

import java.util.ArrayList;

public interface PetBO extends SuperBO {
    ArrayList<Petdto> getAll() throws Exception;
    boolean delete(String petid);
    boolean update(Petdto pet) throws Exception;
    String generate();

}
