package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.PetRecord;

import java.util.ArrayList;

public interface PetRecordDao extends CrudDAO<PetRecord> {
    ArrayList<String> loadId() throws Exception;
}
