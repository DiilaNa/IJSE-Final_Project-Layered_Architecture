package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.MedicineBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.MedicineDAO;
import gdse71.project.animalhospital.dao.custom.MedicineDetailDao;
import gdse71.project.animalhospital.dto.MedicineDto;
import gdse71.project.animalhospital.entity.Medicine;

import java.util.ArrayList;

public class MedicineBOImpl implements MedicineBO {
    MedicineDAO medicineDAO = (MedicineDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINE);
    MedicineDetailDao medicineDetailDao = (MedicineDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINEDETAILS);

    @Override
    public ArrayList<MedicineDto> getALLMedicine() throws Exception {
        ArrayList<MedicineDto> medicineDtos = new ArrayList<>();
        ArrayList<Medicine> medicines = medicineDAO.getAll();
        for (Medicine medicine : medicines) {
            medicineDtos.add(new MedicineDto(
                    medicine.getMedicineId(),
                    medicine.getMedicineName(),
                    medicine.getMedicineCondition(),
                    medicine.getMedicineWeight()
            ));
        }
        return medicineDtos;
    }
}
