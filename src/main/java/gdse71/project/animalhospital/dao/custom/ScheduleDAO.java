package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.Schedule;

import java.util.ArrayList;

public interface ScheduleDAO extends CrudDAO<Schedule> {
    ArrayList<String> loadId() throws Exception;
    String loadName(String ID) throws Exception;
}
