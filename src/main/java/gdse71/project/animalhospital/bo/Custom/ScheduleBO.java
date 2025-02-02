package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.EmpSheduleDto;
import gdse71.project.animalhospital.dto.ScheduleDto;

import java.util.ArrayList;
import java.util.List;

public interface ScheduleBO extends SuperBO {
    ArrayList<ScheduleDto> getAllSchedule() throws Exception;
    boolean saveSchedule( List<ScheduleDto> scheduleDtos,List<EmpSheduleDto> empSheduleDtos) throws Exception;
    boolean updateSchedule(ScheduleDto schedule) throws Exception;
    boolean deleteSchedule(String EmpID,String ScheduleId) throws Exception;
    String setSchedule(String ID) throws Exception;
    String getNextSchedule() throws Exception;
}
