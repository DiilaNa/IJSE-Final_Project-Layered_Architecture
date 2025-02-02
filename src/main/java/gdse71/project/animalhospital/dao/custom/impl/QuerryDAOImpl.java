package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.QuerryDAO;
import gdse71.project.animalhospital.dto.Med_detailDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerryDAOImpl implements QuerryDAO {
    @Override
    public boolean delete(String appointment_id) throws SQLException, ClassNotFoundException {
        return Util.execute("DELETE payment, owner, pet, appointments\n" +
                "            FROM appointments\n" +
                "            LEFT JOIN payment ON payment.payment_id = appointments.pay_id\n" +
                "            LEFT JOIN pet ON pet.pet_id = appointments.petId\n" +
                "            LEFT JOIN owner ON owner.owner_id = pet.ownerid\n" +
                "            WHERE appointments.appointment_id = ?",appointment_id);

    }

}
