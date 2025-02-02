package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.ScheduleDAO;
import gdse71.project.animalhospital.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public ArrayList<Schedule> getAll() throws Exception {
        ResultSet rst = Util.execute("select * from schedule");
        ArrayList<Schedule> schedules = new ArrayList<>();
        while (rst.next()) {
            schedules.add(new Schedule(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return schedules;
    }

    @Override
    public boolean save(Schedule entity) throws Exception {
        return Util.execute("INSERT INTO schedule VALUES(?,?,?)",
                entity.getScheduleID(),
                entity.getDate(),
                entity.getTime()
                );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return Util.execute("DELETE FROM schedule WHERE schedule_id=?",id) ;
    }

    @Override
    public boolean update(Schedule entity) throws Exception {
        return Util.execute(
                "update schedule set  schedule_date=?, schedule_time=?  where schedule_id=?",
                entity.getDate(),
                entity.getTime(),
                entity.getScheduleID()
        );
    }

    @Override
    public String generateId() throws Exception {
        try {
            ResultSet rst = Util.execute("select schedule_id from schedule order by schedule_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1); // Last appointment ID
                String numericPart = lastId.replaceAll("[^0-9]", ""); // Extract numeric part only
                if (numericPart.isEmpty()) {
                    return "SCH001"; // Return default if no numeric part found
                } // Extract the numeric part
                int i = Integer.parseInt(numericPart); // Convert the numeric part to integer
                int newIdIndex = i + 1; // Increment the number by 1
                return String.format("SCH%03d", newIdIndex); // Return the new customer ID in format Cnnn
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
        return "SCH001";
    }

    @Override
    public ArrayList<String> loadId() throws Exception {
        ResultSet rst = Util.execute("select emp_id from employee");
        ArrayList<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString("emp_id"));
        }
        return ids;
    }

    @Override
    public String loadName(String ID) throws Exception {
        ResultSet rst = Util.execute("select emp_name from employee where emp_id=?", ID);
        if (rst.next()) {
            return rst.getString("emp_name");
        }
        return "Found None";
    }

}
