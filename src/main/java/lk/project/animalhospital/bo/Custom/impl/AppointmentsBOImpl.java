package lk.project.animalhospital.bo.Custom.impl;

import lk.project.animalhospital.bo.Custom.AppointmentsBO;
import lk.project.animalhospital.dao.DaoFactory;
import lk.project.animalhospital.dao.custom.AppointmentsDAO;
import lk.project.animalhospital.dao.custom.OwnerDao;
import lk.project.animalhospital.dao.custom.PaymentDao;
import lk.project.animalhospital.dao.custom.PetDao;
import lk.project.animalhospital.db.DBConnection;
import lk.project.animalhospital.dto.Appointmentsdto;
import lk.project.animalhospital.dto.Ownerdto;
import lk.project.animalhospital.dto.PaymentDto;
import lk.project.animalhospital.dto.Petdto;
import lk.project.animalhospital.entity.Appointments;
import lk.project.animalhospital.entity.Owner;
import lk.project.animalhospital.entity.Payment;
import lk.project.animalhospital.entity.Pet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsBOImpl implements AppointmentsBO {
    AppointmentsDAO appointmentsDAO = (AppointmentsDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.APPOINTMENTS);
    OwnerDao ownerDao = (OwnerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.OWNER);
    PaymentDao paymentDao = (PaymentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PAYMENT);
    PetDao petDao = (PetDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PET);

    @Override
    public String getNextAppointmentID() {
        return appointmentsDAO.getNextAppointmentID();
    }

    @Override
    public String getNextPetID() {
        return appointmentsDAO.getNextPetID();
    }

    @Override
    public String getNextOwnerID() {
        return appointmentsDAO.getNextOwnerID();
    }

    @Override
    public String getNextPayID() {
        return appointmentsDAO.getNextPayID();
    }

    @Override
    public boolean CancelApt(String AppointmentSid) throws SQLException, ClassNotFoundException {
        return appointmentsDAO.CancelApt(AppointmentSid);
    }

    @Override
    public boolean saveAppointment( List<Ownerdto> ownerdtos, List<Petdto> petdtos,List<PaymentDto> paymentdtos,List<Appointmentsdto> appointmentsdtos) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            for (Ownerdto ownerdto : ownerdtos) {
                boolean b1 = ownerDao.save(new Owner(
                        ownerdto.getOwnerId(),
                        ownerdto.getOwnerName(),
                        ownerdto.getOwnerAddress(),
                        ownerdto.getOwnerMail()
                ));
                if (!b1) {
                    System.out.println("owner not saved");
                    connection.rollback();
                    return false;
                }
            }

            for (Petdto petdto : petdtos) {
                boolean b2 = petDao.save(new Pet(
                        petdto.getPetId(),
                        petdto.getPetName(),
                        petdto.getPetBreed(),
                        petdto.getPetOwnerId(),
                        petdto.getPetType()
                ));
                if (!b2) {
                    System.out.println("pet not saved");
                    connection.rollback();
                    return false;
                }
            }

            for (PaymentDto paymentdto : paymentdtos) {
                boolean b3 = paymentDao.save(new Payment(
                        paymentdto.getPaymentId(),
                        paymentdto.getPaymentDate(),
                        paymentdto.getPaymentMethodd(),
                        paymentdto.getPaymentTime()
                ));
                if (!b3) {
                    System.out.println("payment not saved");
                    connection.rollback();
                    return false;
                }
            }

            for (Appointmentsdto appointmentsdto : appointmentsdtos) {
                boolean b4 = appointmentsDAO.save(new Appointments(
                        appointmentsdto.getAptID(),
                        appointmentsdto.getAptDate(),
                        appointmentsdto.getAptTime(),
                        appointmentsdto.getPayID(),
                        appointmentsdto.getPet_ID()
                ));
                if (!b4) {
                    System.out.println("apt not saved");
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<String> LoadAppointmentsId() throws SQLException, ClassNotFoundException {
        return appointmentsDAO.LoadId();
    }
}