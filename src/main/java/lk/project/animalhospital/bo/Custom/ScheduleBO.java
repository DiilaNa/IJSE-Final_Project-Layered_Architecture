package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.model.EmpSheduleDto;
import lk.project.animalhospital.model.ScheduleDto;

import java.util.ArrayList;
import java.util.List;

public interface ScheduleBO extends SuperBO {
    ArrayList<ScheduleDto> getAllSchedule() throws Exception;
    boolean saveSchedule( List<ScheduleDto> scheduleDtos,List<EmpSheduleDto> empSheduleDtos) throws Exception;
    boolean updateSchedule(ScheduleDto schedule) throws Exception;
    boolean deleteSchedule(String EmpID,String ScheduleId) throws Exception;
    String setSchedule(String ID) throws Exception;
    String getNextSchedule() throws Exception;
    ArrayList<String> getEmployeeIDs() throws Exception;
    String getEmpName(String ID) throws Exception;
}
