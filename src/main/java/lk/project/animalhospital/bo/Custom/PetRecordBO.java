package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.model.PetRecorddto;


import java.util.ArrayList;

public interface PetRecordBO extends SuperBO {
     ArrayList<PetRecorddto> getAllpetRecords() throws Exception;
     boolean savePetRecords(PetRecorddto petRecord) throws Exception;
     boolean deletePetRecords(String id) throws Exception;
     boolean updatePetRecords(PetRecorddto petRecord) throws Exception;
     ArrayList<String> loadPetids() throws Exception;
      String generateNextRecordId() throws Exception;
}
