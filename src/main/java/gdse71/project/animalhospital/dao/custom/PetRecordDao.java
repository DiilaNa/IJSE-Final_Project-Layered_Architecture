package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.PetRecord;

import java.util.ArrayList;

public interface PetRecordDao extends CrudDAO<PetRecord> {
    ArrayList<String> loadId() throws Exception, ClassNotFoundException;
}
