package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Med_detailDto;
import gdse71.project.animalhospital.dto.MedicineDto;
import gdse71.project.animalhospital.entity.Medicine;

import java.util.ArrayList;
import java.util.List;

public interface MedicineBO extends SuperBO {
    ArrayList<MedicineDto> getALLMedicine() throws Exception;
    boolean saveMedicine(List<MedicineDto>medicineDtos,List<Med_detailDto> medDetailDtos) throws Exception;
    boolean updateMedicine(MedicineDto medicineDto) throws Exception;
    boolean deleteMedicine(String medID,String MedDetailId) throws Exception;
    String getNextMedID() throws Exception;
    ArrayList<String> getMedIdComboBox() throws Exception;
    String getPetName(String petID) throws Exception;
    String getPetID(String ID) throws Exception;

}
