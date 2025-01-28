package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.bo.Custom.MedicineBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.MedicineDAO;
import gdse71.project.animalhospital.dao.custom.MedicineDetailDao;
import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.Med_detailDto;
import gdse71.project.animalhospital.dto.MedicineDto;
import gdse71.project.animalhospital.entity.Medicine;
import gdse71.project.animalhospital.entity.MedicineDetails;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean saveMedicine(List<MedicineDto> medicineDtos, List<Med_detailDto> medDetailDtos) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        for (MedicineDto medicineDto : medicineDtos) {
            boolean b1 = medicineDAO.save(new Medicine(
                    medicineDto.getMedicineId(),
                    medicineDto.getMedicineName(),
                    medicineDto.getMedicineCondition(),
                    medicineDto.getMedicineWeight()
            ));
            if (!b1){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        for (Med_detailDto medDetailDto : medDetailDtos) {
            boolean b2 = medicineDetailDao.save(new MedicineDetails(
               medDetailDto.getMeDID(),
               medDetailDto.getPETID()
            ));
            if (!b2){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public boolean updateMedicine(MedicineDto medicineDto) throws Exception {
        return medicineDAO.update(new Medicine(
                medicineDto.getMedicineId(),
                medicineDto.getMedicineName(),
                medicineDto.getMedicineCondition(),
                medicineDto.getMedicineWeight()
        ));
    }

    @Override
    public boolean deleteMedicine(String medID, String MedDetailId) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean b1 = medicineDAO.delete(medID);
        if (!b1){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        boolean b2 = medicineDetailDao.delete(MedDetailId);
        if (!b2){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }
}
