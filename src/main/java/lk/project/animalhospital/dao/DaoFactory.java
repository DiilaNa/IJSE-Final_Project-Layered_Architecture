package lk.project.animalhospital.dao;

import lk.project.animalhospital.dao.custom.impl.*;

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
        PET, PET_RECORD,OWNER,PAYMENT,INVOICE,SALARY,SERVICE,SMS,MEDICINE, MEDICINE_DETAILS,QUERY,EMPLOYEE,DOC_DETAIL,SCHEDULE,EMP_SCHEDULE,APPOINTMENTS,VIEW_APPOINTMENTS
    }
    public SuperDAO getDao(DaoType Type) {
        switch (Type) {
            case PET:
                return new PetDaoImpl();
                case PET_RECORD:
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
                                                case MEDICINE_DETAILS:
                                                    return new MedicineDetailsDAOImpl();
                                                      case QUERY:
                                                        return new QuerryDAOImpl();
                                                        case EMPLOYEE:
                                                            return new EmployeeDAOImpl();
                                                            case DOC_DETAIL:
                                                                return new DocDetailDAOImpl();
                                                                case SCHEDULE:
                                                                    return new ScheduleDAOImpl();
                                                                    case EMP_SCHEDULE:
                                                                        return new EmpScheduleDAOImpl();
                                                                        case APPOINTMENTS:
                                                                            return new AppointmentsDAOImpl();
                                                                            case VIEW_APPOINTMENTS:
                                                                                return new ViewAppointmentsDAOImpl();
                default:
                    return null;
        }

    }

}
