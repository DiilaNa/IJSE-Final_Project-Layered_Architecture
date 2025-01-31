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
    public boolean save(Employee entity) throws Exception {
       return Util.execute("insert into employee values(?,?,?,?,?)",
               entity.getEmployeeId(),
               entity.getEmployeeName(),
               entity.getEmployeeDuty(),
               entity.getEmployeeAddress(),
               entity.getEmployeePhone()

       );
    }

    @Override
    public boolean delete(String id) throws Exception {
       return Util.execute("DELETE FROM employee WHERE emp_id=?",id);
    }

    @Override
    public boolean update(Employee entity) throws Exception {
        return Util.execute("UPDATE employee SET emp_name=?, duty=? , address=? , tel_no=? WHERE emp_id=? ",
                    entity.getEmployeeName(),
                    entity.getEmployeeDuty(),
                    entity.getEmployeeAddress(),
                    entity.getEmployeePhone(),
                    entity.getEmployeeId()
                );
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }
}
