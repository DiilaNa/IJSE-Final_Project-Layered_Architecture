package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.EmployeeDAO;
import gdse71.project.animalhospital.dto.Employeedto;
import gdse71.project.animalhospital.entity.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList getAll() throws Exception {
        ResultSet rst = Util.execute("select * from employee");
        ArrayList<Employee> employdtos = new ArrayList<>();
        while (rst.next()) {
            employdtos.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return employdtos;
    }

    @Override
    public boolean save(Object dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(Object dto) throws Exception {
        return false;
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }
}
