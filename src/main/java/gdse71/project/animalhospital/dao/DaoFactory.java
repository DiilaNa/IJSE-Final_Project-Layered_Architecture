package gdse71.project.animalhospital.dao;

import gdse71.project.animalhospital.dao.custom.PetDao;
import gdse71.project.animalhospital.dao.custom.impl.PetDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory() {

    }
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
    public enum DaoType {
        PET
    }
    public SuperDAO getDao(DaoType Type) {
        switch (Type) {
            case PET:
                return new PetDaoImpl();
                default:
                    return null;
        }

    }

}
