package lk.project.animalhospital.bo;

import lk.project.animalhospital.bo.Custom.impl.*;

public class BOFactory{
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getInstance(){
        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOType{
        PET, PET_RECORD,OWNER,PAYMENT,INVOICE,SALARY,SERVICE,SMS,MEDICINE,EMPLOYEE,SCHEDULE,APPOINTMENTS,VIEW_APPOINTMENTS
    }
    public SuperBO getBO(BOType type){
        switch (type){
            case PET:
                return new PetBOImpl();
                case PET_RECORD:
                    return new PetRecordBOImpl();
                        case OWNER:
                            return new OwnerBOImpl();
                            case PAYMENT:
                                return new PaymentBOImpl();
                                case INVOICE:
                                    return new InvoiceBOImpl();
                                    case SALARY:
                                        return new SalaryBOImpl();
                                        case SERVICE:
                                            return new ServiceBOImpl();
                                            case SMS:
                                                return new SmsBOImpl();
                                                case MEDICINE:
                                                    return new MedicineBOImpl();
                                                    case EMPLOYEE:
                                                        return new EmployeeBOImpl();
                                                        case SCHEDULE:
                                                            return new ScheduleBOImpl();
                                                            case APPOINTMENTS:
                                                                return new AppointmentsBOImpl();
                                                                case VIEW_APPOINTMENTS:
                                                                    return new ViewAppointmentsBOImpl();

                default:
                    return null;
        }
    }
}
