package lk.project.animalhospital.bo.Custom.impl;


import lk.project.animalhospital.bo.Custom.PetRecordBO;
import lk.project.animalhospital.dao.DaoFactory;
import lk.project.animalhospital.dao.custom.PetRecordDao;
import lk.project.animalhospital.model.PetRecorddto;
import lk.project.animalhospital.entity.PetRecord;

import java.util.ArrayList;

public class PetRecordBOImpl implements PetRecordBO {

    PetRecordDao petRecordDao = (PetRecordDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PET_RECORD);

    @Override
    public ArrayList<PetRecorddto> getAllpetRecords() throws Exception {

       ArrayList <PetRecorddto> petRecordDtos = new ArrayList<>();
       ArrayList<PetRecord> petRecords = petRecordDao.getAll();

       for (PetRecord petRecord : petRecords) {
           petRecordDtos.add(new PetRecorddto(
                   petRecord.getRecordId(),
                   petRecord.getStatus(),
                   petRecord.getDescription(),
                   petRecord.getWeight(),
                   petRecord.getPetID()));
       }
       return petRecordDtos;
    }

    @Override
    public boolean savePetRecords(PetRecorddto petRecord) throws Exception {
       return petRecordDao.save(new PetRecord(
               petRecord.getRecordId(),
               petRecord.getStatus(),
               petRecord.getDescription(),
               petRecord.getWeight(),
               petRecord.getPetID()));
    }

    @Override
    public boolean deletePetRecords(String id) throws Exception {
        return petRecordDao.delete(id);
    }

    @Override
    public boolean updatePetRecords(PetRecorddto petRecord) throws Exception {
        return petRecordDao.update(new PetRecord(
                petRecord.getRecordId(),
                petRecord.getStatus(),
                petRecord.getDescription(),
                petRecord.getWeight(),
                petRecord.getPetID()
        ));
    }
    @Override
    public ArrayList<String> loadPetids() throws Exception {
        return petRecordDao.loadId();
    }

    @Override
    public String generateNextRecordId() throws Exception {
        return petRecordDao.generateId();
    }

}
