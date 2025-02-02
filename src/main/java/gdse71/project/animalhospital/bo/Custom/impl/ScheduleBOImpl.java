package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.ScheduleBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.EmpScheduleDAO;
import gdse71.project.animalhospital.dao.custom.ScheduleDAO;
import gdse71.project.animalhospital.dao.custom.impl.ScheduleDAOImpl;
import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.EmpSheduleDto;
import gdse71.project.animalhospital.dto.ScheduleDto;
import gdse71.project.animalhospital.entity.EmpSchedule;
import gdse71.project.animalhospital.entity.Schedule;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ScheduleBOImpl implements ScheduleBO {
    ScheduleDAO scheduleDAO = (ScheduleDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.SCHEDULE);
    EmpScheduleDAO empScheduleDAO = (EmpScheduleDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMP_SCHEDULE);

    @Override
    public ArrayList<ScheduleDto> getAllSchedule() throws Exception {
        ArrayList<ScheduleDto>scheduleDtos = new ArrayList<>();
        ArrayList<Schedule>schedules = scheduleDAO.getAll();
        for (Schedule schedule : schedules) {
            scheduleDtos.add(new ScheduleDto(
                    schedule.getScheduleID(),
                    schedule.getDate(),
                    schedule.getTime()
            ));
        }
        return scheduleDtos;
    }


    @Override
    public boolean saveSchedule(List<ScheduleDto> scheduleDtos,List<EmpSheduleDto> empSheduleDtos) throws Exception {
        Connection connection = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            for (ScheduleDto scheduleDto : scheduleDtos) {
                boolean b1 = scheduleDAO.save(new Schedule(
                   scheduleDto.getScheduleID(),
                   scheduleDto.getDate(),
                   scheduleDto.getTime()
                ));
                if (!b1) {
                    connection.rollback();
                    return true;
                }
            }
            for (EmpSheduleDto empSheduleDto : empSheduleDtos) {
                boolean b2 = empScheduleDAO.save(new EmpSchedule(
                   empSheduleDto.getEmpid(),
                   empSheduleDto.getSid()
                ));
                if (!b2) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (RuntimeException e) {
            if (connection != null) {
                connection.rollback();

            }
            return false;
        }
    }

    @Override
    public boolean updateSchedule(ScheduleDto schedule) throws Exception {
        return scheduleDAO.update(new Schedule(
                schedule.getScheduleID(),
                schedule.getDate(),
                schedule.getTime()
        ));
    }

    @Override
    public boolean deleteSchedule( String ScheduleId,String EmpID) throws Exception {
        Connection connection = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean b2 = empScheduleDAO.delete(EmpID);

            if (!b2) {
                connection.rollback();
                return false;
            }

            boolean b1 = scheduleDAO.delete(ScheduleId);
            if (!b1) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (RuntimeException e) {
            if (connection != null) {
                connection.rollback();
            }
            return false;
        }
    }

    @Override
    public String setSchedule(String ID) throws Exception {
        return empScheduleDAO.searchEmID(ID);
    }

    @Override
    public String getNextSchedule() throws Exception {
        return scheduleDAO.generateId();
    }

    @Override
    public ArrayList<String> getEmployeeIDs() throws Exception {
        return scheduleDAO.loadId();
    }

    @Override
    public String getEmpName(String ID) throws Exception {
        return scheduleDAO.loadName(ID);
    }
}
