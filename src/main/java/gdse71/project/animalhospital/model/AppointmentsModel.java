package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class

AppointmentsModel {
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

