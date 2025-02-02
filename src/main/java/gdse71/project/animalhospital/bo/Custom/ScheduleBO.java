package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.EmpSheduleDto;
import gdse71.project.animalhospital.dto.ScheduleDto;

import java.util.ArrayList;
import java.util.List;

public interface ScheduleBO extends SuperBO {
    ArrayList<ScheduleDto> getAllSchedule() throws Exception;
    boolean saveSchedule( List<ScheduleDto> scheduleDtos,List<EmpSheduleDto> empSheduleDtos) throws Exception;
}
