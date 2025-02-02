package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.ScheduleDAO;
import gdse71.project.animalhospital.entity.Schedule;

import java.sql.ResultSet;
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
        return false ;
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
        return "";
    }
}
