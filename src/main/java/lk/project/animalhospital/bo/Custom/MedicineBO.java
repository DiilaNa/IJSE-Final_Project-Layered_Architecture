package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.dto.Med_detailDto;
import lk.project.animalhospital.dto.MedicineDto;

import java.util.ArrayList;
import java.util.List;

public interface MedicineBO extends SuperBO {
    ArrayList<MedicineDto> getALLMedicine() throws Exception;
    boolean saveMedicine(List<MedicineDto>medicineDtos,List<Med_detailDto> medDetailDtos) throws Exception;
    boolean updateMedicine(MedicineDto medicine) throws Exception;
    boolean deleteMedicine(String medID,String MedDetailId) throws Exception;
    String getNextMedID() throws Exception;
    ArrayList<String> getMedIdComboBox() throws Exception;
    String getPetName(String petID) throws Exception;
    String getPetID(String ID) throws Exception;

}
