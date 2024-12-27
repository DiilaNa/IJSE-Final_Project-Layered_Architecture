package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentsModel {
    public String getNextAppointmentID() throws SQLException {
        try {
            ResultSet rst = Util.execute("select appointment_id from appointments order by appointment_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1); // Last appointment ID
                String numericPart = lastId.replaceAll("[^0-9]", ""); // Extract numeric part only
                if (numericPart.isEmpty()) {
                    return "APT001"; // Return default if no numeric part found
                } // Extract the numeric part
                int i = Integer.parseInt(numericPart); // Convert the numeric part to integer
                int newIdIndex = i + 1; // Increment the number by 1
                return String.format("APT%03d", newIdIndex); // Return the new customer ID in format Cnnn
            }
        } catch (ClassNotFoundException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return "APT001"; // Return the default customer ID if no data is found
    }
    public String  getNextPetId() {
        try {
            ResultSet rst = Util.execute("select pet_id from pet order by pet_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1); // Last appointment ID
                String numericPart = lastId.replaceAll("[^0-9]", ""); // Extract numeric part only
                if (numericPart.isEmpty()) {
                    return "PET001"; // Return default if no numeric part found
                } // Extract the numeric part
                int i = Integer.parseInt(numericPart); // Convert the numeric part to integer
                int newIdIndex = i + 1; // Increment the number by 1
                return String.format("PET%03d", newIdIndex); // Return the new customer ID in format Cnnn
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
        return "PET001"; // Return the default customer ID if no data is found
    }
    public String  getNextOwnerId(){
        try {
            ResultSet rst = Util.execute("select owner_id from owner order by owner_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1); // Last appointment ID
                String numericPart = lastId.replaceAll("[^0-9]", ""); // Extract numeric part only
                if (numericPart.isEmpty()) {
                    return "OWN001"; // Return default if no numeric part found
                } // Extract the numeric part
                int i = Integer.parseInt(numericPart); // Convert the numeric part to integer
                int newIdIndex = i + 1; // Increment the number by 1
                return String.format("OWN%03d", newIdIndex); // Return the new customer ID in format Cnnn
            }
        } catch (ClassNotFoundException | NumberFormatException |SQLException e) {
            throw new RuntimeException(e);
        }
        return "OWN001"; // Return the default customer ID if no data is found
    }
    public String getNextPayId(){
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
    public boolean save(Appointmentsdto appointmentsdto, Petdto petdto , Ownerdto ownerdto , PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
            Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection(); // Obtain the database connection
                connection.setAutoCommit(false); // Start transaction

                // Save owner details
                PreparedStatement ownerStmt = connection.prepareStatement("INSERT INTO owner (owner_id, name, address, email) VALUES (?, ?, ?, ?)");
                ownerStmt.setString(1, ownerdto.getOwnerId());
                ownerStmt.setString(2, ownerdto.getOwnerName());
                ownerStmt.setString(3, ownerdto.getOwnerAddress());
                ownerStmt.setString(4, ownerdto.getOwnerMail());
                ownerStmt.executeUpdate();

                // Save pet details
                PreparedStatement petStmt = connection.prepareStatement("INSERT INTO pet (pet_id, pet_name, breed, ownerid,Pet_Type) VALUES (?, ?, ?, ?, ?)");
                petStmt.setString(1, petdto.getPetId());
                petStmt.setString(2, petdto.getPetName());
                petStmt.setString(3, petdto.getPetBreed());
                petStmt.setString(4, petdto.getPetOwnerId());
                petStmt.setString(5, petdto.getPetType());
                petStmt.executeUpdate();



                // Save payment details
                PreparedStatement paymentStmt = connection.prepareStatement("INSERT INTO payment (payment_id, payment_date, payment_method,payment_time) VALUES (?, ?, ?, ?)");
                paymentStmt.setString(1, paymentDto.getPaymentId());
                paymentStmt.setString(2, paymentDto.getPaymentDate());
                paymentStmt.setString(3, paymentDto.getPaymentMethodd());
                paymentStmt.setString(4, paymentDto.getPaymentTime());
                paymentStmt.executeUpdate();

                // Save appointment details
                PreparedStatement appointmentStmt = connection.prepareStatement("INSERT INTO appointments (appointment_id,appointment_date,appointment_time, pay_id, petId) VALUES (?, ?, ?, ?, ?)");
                appointmentStmt.setString(1, appointmentsdto.getAptID());
                appointmentStmt.setString(2, appointmentsdto.getAptDate());
                appointmentStmt.setString(3, appointmentsdto.getAptTime());
                appointmentStmt.setString(4, appointmentsdto.getPayID());
                appointmentStmt.setString(5, appointmentsdto.getPet_ID());
                appointmentStmt.executeUpdate();


                connection.commit(); // Commit transaction if all inserts are successful
                return true;
            } catch (SQLException e) {
                if (connection != null) {
                    connection.rollback(); // Rollback transaction in case of an error
                }
                e.printStackTrace();
                return false;
            }

    }

    public static boolean CancelApt(String AppointmentSid) throws SQLException, ClassNotFoundException {
        return  Util.execute("UPDATE appointments set isCancelled = 'APPOINTMENT CANCELLED' WHERE appointment_id = ?", AppointmentSid);

    }

}

