package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.dao.custom.ScheduleDAO;
import gdse71.project.animalhospital.entity.Schedule;

import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public ArrayList<Schedule> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Schedule dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(Schedule dto) throws Exception {
        return false;
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }
}
