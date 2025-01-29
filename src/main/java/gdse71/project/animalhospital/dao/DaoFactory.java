package gdse71.project.animalhospital.dao;

import gdse71.project.animalhospital.dao.custom.MedicineDetailDao;
import gdse71.project.animalhospital.dao.custom.PetDao;
import gdse71.project.animalhospital.dao.custom.impl.*;

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
        PET,PETRECORD,OWNER,PAYMENT,INVOICE,SALARY,SERVICE,SMS,MEDICINE,MEDICINEDETAILS,JOIN
    }
    public SuperDAO getDao(DaoType Type) {
        switch (Type) {
            case PET:
                return new PetDaoImpl();
                case PETRECORD:
                    return new PetRecordImpl();
                    case OWNER:
                        return new OwnerDAOImpl();
                        case PAYMENT:
                            return new PaymentDAOImpl();
                            case INVOICE:
                                return new InvoiceDAOImpl();
                                case SALARY:
                                    return new SalaryDAOImpl();
                                    case SERVICE:
                                        return new ServiceDAOImpl();
                                        case SMS:
                                            return new SmsDAOImpl();
                                            case MEDICINE:
                                                return new MedicineDAOImpl();
                                                case MEDICINEDETAILS:
                                                    return new MedicineDetailsDAOImpl();
                                                    case JOIN:
                                                        return new QuerryDAOImpl();
                default:
                    return null;
        }

    }

}
