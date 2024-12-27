package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.ScheduleDto;
import gdse71.project.animalhospital.dto.Smsdto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SmsModel {
    public ArrayList<Smsdto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  Util.execute("select * from mail_reminder");

        ArrayList<Smsdto> smsdtos = new ArrayList<>();

        while (rst.next()){
            Smsdto smsdto = new Smsdto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            );
            smsdtos.add(smsdto);
        }
        return smsdtos;
    }
    public boolean save(Smsdto smsdto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "insert into mail_reminder values (?,?,?,?)",
                smsdto.getSmsNo(),
                smsdto.getDate(),
                smsdto.getStatus(),
                smsdto.getAppID()
        );
    }
    public boolean update(Smsdto smsdto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "update mail_reminder set  mail_date=?, status=? , app_id=? where mail_no=?",
                smsdto.getDate(),
                smsdto.getStatus(),
                smsdto.getAppID(),
                smsdto.getSmsNo()
                );
    }

    public boolean delete(String mail_no) throws SQLException, ClassNotFoundException {
        return Util.execute("delete from mail_reminder where mail_no=?", mail_no);
    }
    public String  getNextMailNo() throws SQLException, ClassNotFoundException {
        try {
            ResultSet rst = Util.execute("select mail_no from mail_reminder order by mail_no desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1); // Last appointment ID
                String numericPart = lastId.replaceAll("[^0-9]", ""); // Extract numeric part only
                if (numericPart.isEmpty()) {
                    return "SMS001"; // Return default if no numeric part found
                } // Extract the numeric part
                int i = Integer.parseInt(numericPart); // Convert the numeric part to integer
                int newIdIndex = i + 1; // Increment the number by 1
                return String.format("SMS%03d", newIdIndex); // Return the new customer ID in format Cnnn
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
        return "SMS001";
    }

}
