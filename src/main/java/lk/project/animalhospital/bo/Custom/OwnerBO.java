package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.dto.Ownerdto;

import java.util.ArrayList;

public interface OwnerBO extends SuperBO {
     ArrayList<Ownerdto> getAllOwner() throws Exception ;
     boolean deleteOwners(String id) throws Exception ;
     boolean updateOwners(Ownerdto owner) throws Exception ;
}
