package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.AppointmentsDAO;
import gdse71.project.animalhospital.entity.Appointments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentsDAOImpl implements AppointmentsDAO {
    @Override
    public ArrayList<Appointments> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Appointments entity) throws Exception {
        return Util.execute("INSERT INTO appointments (appointment_id,appointment_date,appointment_time, pay_id, petId) VALUES (?, ?, ?, ?, ?)",
                entity.getAptID(),
                entity.getAptDate(),
                entity.getAptTime(),
                entity.getPayID(),
                entity.getPet_ID()
                );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(Appointments entity) throws Exception {
        return false;
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }

    @Override
    public String getNextAppointmentID() {
        try {
            ResultSet rst = Util.execute("select appointment_id from appointments order by appointment_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1);
                String numericPart = lastId.replaceAll("[^0-9]", "");
                if (numericPart.isEmpty()) {
                    return "APT001";
                }
                int i = Integer.parseInt(numericPart);
                int newIdIndex = i + 1;
                return String.format("APT%03d", newIdIndex);
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
        return "APT001";
    }

    @Override
    public String getNextPetID() {
        try {
            ResultSet rst = Util.execute("select pet_id from pet order by pet_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1);
                String numericPart = lastId.replaceAll("[^0-9]", "");
                if (numericPart.isEmpty()) {
                    return "PET001";
                }
                int i = Integer.parseInt(numericPart);
                int newIdIndex = i + 1;
                return String.format("PET%03d", newIdIndex);
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
        return "PET001";
    }

    @Override
    public String getNextOwnerID() {
        try {
            ResultSet rst = Util.execute("select owner_id from owner order by owner_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1);
                String numericPart = lastId.replaceAll("[^0-9]", "");
                if (numericPart.isEmpty()) {
                    return "OWN001";
                }
                int i = Integer.parseInt(numericPart);
                int newIdIndex = i + 1;
                return String.format("OWN%03d", newIdIndex);
            }
        } catch (ClassNotFoundException | NumberFormatException |SQLException e) {
            throw new RuntimeException(e);
        }
        return "OWN001";
    }

    @Override
    public String getNextPayID() {
        try {
            ResultSet rst = Util.execute("select payment_id from payment order by payment_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1);
                String numericPart = lastId.replaceAll("[^0-9]", "");
                if (numericPart.isEmpty()) {
                    return "PAY001";
                }
                int i = Integer.parseInt(numericPart);
                int newIdIndex = i + 1;
                return String.format("PAY%03d", newIdIndex);
            }
        } catch (ClassNotFoundException | NumberFormatException |SQLException e) {
            throw new RuntimeException(e);
        }
        return "PAY001";
    }

    @Override
    public boolean CancelApt(String id) throws SQLException, ClassNotFoundException {
        return  Util.execute("UPDATE appointments set isCancelled = 'APPOINTMENT CANCELLED' WHERE appointment_id = ?", id);

    }
}
