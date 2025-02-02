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
    public static boolean CancelApt(String AppointmentSid) throws SQLException, ClassNotFoundException {
        return  Util.execute("UPDATE appointments set isCancelled = 'APPOINTMENT CANCELLED' WHERE appointment_id = ?", AppointmentSid);

    }

}

