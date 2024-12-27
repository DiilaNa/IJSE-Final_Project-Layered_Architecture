package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.ViewAppointmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewAppointmentModel {
    public ArrayList<ViewAppointmentDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  Util.execute("select * from appointments");

        ArrayList<ViewAppointmentDto> viewAppointmentDtos = new ArrayList<>();

        while (rst.next()){
            ViewAppointmentDto viewAppointmentDto = new ViewAppointmentDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)

            );
            viewAppointmentDtos.add(viewAppointmentDto);
        }
        return viewAppointmentDtos;
    }
    public boolean delete(String appointment_id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        boolean success = false;

        try {
            conn = Util.getConnection();  // Assuming Util has a method to get a DB connection
            conn.setAutoCommit(false);    // Start transaction

            String deleteQuery = """
            DELETE payment, owner, pet, appointments
            FROM appointments
            LEFT JOIN payment ON payment.payment_id = appointments.pay_id
            LEFT JOIN pet ON pet.pet_id = appointments.petId
            LEFT JOIN owner ON owner.owner_id = pet.ownerid
            WHERE appointments.appointment_id = ?;
        """;

            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setString(1, appointment_id); // Set the appointment_id parameter
                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows affected by delete: " + rowsAffected);

                if (rowsAffected > 0) {
                    conn.commit(); // Commit the transaction
                    success = true;
                } else {
                    conn.rollback(); // Rollback if no rows were affected
                }
            }

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();  // Ensure rollback in case of error
            }
            e.printStackTrace();
        }
        return success;
    }

}
