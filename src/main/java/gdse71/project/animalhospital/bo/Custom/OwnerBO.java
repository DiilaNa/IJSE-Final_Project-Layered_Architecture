package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Ownerdto;
import gdse71.project.animalhospital.entity.Owner;
import java.util.ArrayList;

public interface OwnerBO extends SuperBO {
     ArrayList<Ownerdto> getAllOwner() throws Exception ;
     boolean deleteOwners(String id) throws Exception ;
     boolean updateOwners(Ownerdto owner) throws Exception ;
}
