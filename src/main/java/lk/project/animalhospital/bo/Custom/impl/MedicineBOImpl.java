package lk.project.animalhospital.bo.Custom.impl;

import lk.project.animalhospital.bo.Custom.MedicineBO;
import lk.project.animalhospital.dao.DaoFactory;
import lk.project.animalhospital.dao.custom.MedicineDAO;
import lk.project.animalhospital.dao.custom.MedicineDetailDao;
import lk.project.animalhospital.dao.custom.QuerryDAO;
import lk.project.animalhospital.db.DBConnection;
import lk.project.animalhospital.model.Med_detailDto;
import lk.project.animalhospital.model.MedicineDto;
import lk.project.animalhospital.entity.Medicine;
import lk.project.animalhospital.entity.MedicineDetails;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MedicineBOImpl implements MedicineBO {
    MedicineDAO medicineDAO = (MedicineDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINE);
    MedicineDetailDao medicineDetailDao = (MedicineDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICINE_DETAILS);
    QuerryDAO querryDAO = (QuerryDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);

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
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            for (MedicineDto medicineDto : medicineDtos) {
                boolean b1 = medicineDAO.save(new Medicine(
                        medicineDto.getMedicineId(),
                        medicineDto.getMedicineName(),
                        medicineDto.getMedicineCondition(),
                        medicineDto.getMedicineWeight()
                ));
                if (!b1) {
                    connection.rollback();
                    return false;
                }
            }
            for (Med_detailDto medDetailDto : medDetailDtos) {
                boolean b2 = medicineDetailDao.save(new MedicineDetails(
                        medDetailDto.getMeDID(),
                        medDetailDto.getPETID()
                ));
                if (!b2) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMedicine(MedicineDto medicine) throws Exception {
        return medicineDAO.update(new Medicine(
                medicine.getMedicineId(),
                medicine.getMedicineName(),
                medicine.getMedicineCondition(),
                medicine.getMedicineWeight()
        ));
    }

    @Override
    public boolean deleteMedicine(String medID, String PetIDValue) throws Exception {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);


        boolean b1 = medicineDetailDao.delete(PetIDValue);
            if (!b1) {
            connection.rollback();
            return false;
            }

        boolean b2 = medicineDAO.delete(medID);
            if (!b2) {
                connection.rollback();
                return false;
            }

        connection.commit();
        return true;
    } catch (Exception e) {
            if (connection != null) {
                connection.rollback(); // Rollback transaction on any exception
            }
            throw new Exception("Failed to delete medicine and details: " + e.getMessage(), e);
        }
    }

    @Override
    public String getNextMedID() throws Exception {
        return medicineDAO.generateId();
    }

    @Override
    public ArrayList<String> getMedIdComboBox() throws Exception {
        return medicineDAO.loadPetID();
    }

    @Override
    public String getPetName( String petID) throws Exception {
        return medicineDAO.searchPetName(petID);
    }

    @Override
    public String getPetID(String ID) throws Exception {
        return medicineDetailDao.searchPetID(ID);
    }
}
