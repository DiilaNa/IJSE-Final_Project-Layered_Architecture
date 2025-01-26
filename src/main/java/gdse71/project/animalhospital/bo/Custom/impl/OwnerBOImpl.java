package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.OwnerBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.OwnerDao;
import gdse71.project.animalhospital.dto.Ownerdto;
import gdse71.project.animalhospital.entity.Owner;

import java.util.ArrayList;

public class OwnerBOImpl implements OwnerBO {

    OwnerDao ownerDao = (OwnerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.OWNER);
    @Override
    public ArrayList<Ownerdto> getAll() throws Exception, ClassNotFoundException {
        ArrayList<Ownerdto> ownerdtos = new ArrayList<>();
        ArrayList<Owner> owners = ownerDao.getAll();
        for (Owner owner : owners) {
           ownerdtos.add(new Ownerdto(owner.getOwnerId(), owner.getOwnerName(), owner.getOwnerAddress(), owner.getOwnerMail()));
        }
        return ownerdtos;
    }
    @Override
    public boolean delete(String id) throws Exception, ClassNotFoundException {
        return ownerDao.delete(id);
    }

    @Override
    public boolean update(Ownerdto owner) throws Exception, ClassNotFoundException {
        return ownerDao.update(new Owner(
                owner.getOwnerId(), owner.getOwnerName(), owner.getOwnerAddress(), owner.getOwnerMail()
        ));
    }

    @Override
    public String generateId() throws Exception, ClassNotFoundException {
        return "";
    }
}
