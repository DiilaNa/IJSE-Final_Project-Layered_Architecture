package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Ownerdto;
import gdse71.project.animalhospital.entity.Owner;
import java.util.ArrayList;

public interface OwnerBO extends SuperBO {
     ArrayList<Ownerdto> getAll() throws Exception ;
     boolean delete(String id) throws Exception ;
     boolean update(Ownerdto owner) throws Exception ;
     String generateId() throws Exception ;
}
