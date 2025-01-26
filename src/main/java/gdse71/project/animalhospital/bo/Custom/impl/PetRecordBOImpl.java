package gdse71.project.animalhospital.bo.Custom.impl;


import gdse71.project.animalhospital.bo.Custom.PetRecordBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.PetRecordDao;
import gdse71.project.animalhospital.dto.PetRecorddto;
import gdse71.project.animalhospital.entity.PetRecord;

import java.util.ArrayList;

public class PetRecordBOImpl implements PetRecordBO {

    PetRecordDao petRecordDao = (PetRecordDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PETRECORD);

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

}
