package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.MedicineDto;
import gdse71.project.animalhospital.entity.Medicine;

import java.util.ArrayList;

public interface MedicineBO extends SuperBO {
    ArrayList<MedicineDto> getALLMedicine() throws Exception;
}
