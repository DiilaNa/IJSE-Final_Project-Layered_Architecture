package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.PetRecorddto;
import gdse71.project.animalhospital.entity.PetRecord;


import java.util.ArrayList;

public interface PetRecordBO extends SuperBO {
     ArrayList<PetRecorddto> getAll() throws Exception;
     boolean save(PetRecorddto petRecord) throws Exception;
     boolean delete(String id) throws Exception;
     boolean update(PetRecorddto petRecord) throws Exception;
}
