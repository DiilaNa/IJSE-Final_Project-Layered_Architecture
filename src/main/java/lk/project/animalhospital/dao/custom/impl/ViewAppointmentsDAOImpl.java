package lk.project.animalhospital.dao.custom.impl;

import lk.project.animalhospital.CrudUtil.Util;
import lk.project.animalhospital.dao.custom.ViewAppointmentsDAO;
import lk.project.animalhospital.entity.ViewAppointments;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ViewAppointmentsDAOImpl implements ViewAppointmentsDAO {
    @Override
    public ArrayList<ViewAppointments> getAll() throws Exception {
        ResultSet rst = Util.execute("SELECT * FROM appointments");
        ArrayList<ViewAppointments>viewAppointments=new ArrayList<>();
        while (rst.next()) {
            viewAppointments.add(new ViewAppointments(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return viewAppointments;
    }

    @Override
    public boolean save(ViewAppointments dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(ViewAppointments dto) throws Exception {
        return false;
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }
}
