package lk.project.animalhospital.dao.custom.impl;

import lk.project.animalhospital.dao.Util;
import lk.project.animalhospital.dao.custom.EmpScheduleDAO;
import lk.project.animalhospital.entity.EmpSchedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpScheduleDAOImpl implements EmpScheduleDAO {
    @Override
    public ArrayList<EmpSchedule> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(EmpSchedule entity) throws Exception {
        return Util.execute("INSERT INTO employee_schedule VALUE(?,?) ",
                    entity.getEmpid(),
                    entity.getSid()
                );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return Util.execute("DELETE FROM employee_schedule WHERE e_id=?",id) ;
    }

    @Override
    public boolean update(EmpSchedule entity) throws Exception {
        return false;
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }

    @Override
    public String searchEmID(String ID) throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT e_id FROM employee_schedule WHERE s_id=?",ID);
        if (rst.next()){
            return rst.getString("e_id");
        }
        return "NO NAME FOUND";
    }
}
