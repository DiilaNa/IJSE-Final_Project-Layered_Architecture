package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.PetRecorddto;
import gdse71.project.animalhospital.entity.PetRecord;


import java.util.ArrayList;

public interface PetRecordBO extends SuperBO {
     ArrayList<PetRecorddto> getAllpetRecords() throws Exception;
     boolean savePetRecords(PetRecorddto petRecord) throws Exception;
     boolean deletePetRecords(String id) throws Exception;
     boolean updatePetRecords(PetRecorddto petRecord) throws Exception;
}
