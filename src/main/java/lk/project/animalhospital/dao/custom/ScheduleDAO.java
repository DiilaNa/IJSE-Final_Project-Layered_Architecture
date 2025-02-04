package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.Schedule;

import java.util.ArrayList;

public interface ScheduleDAO extends CrudDAO<Schedule> {
    ArrayList<String> loadId() throws Exception;
    String loadName(String ID) throws Exception;
}
