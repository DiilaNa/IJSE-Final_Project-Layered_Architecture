package lk.project.animalhospital.dao.custom.impl;

import lk.project.animalhospital.CrudUtil.Util;
import lk.project.animalhospital.dao.custom.EmployeeDAO;
import lk.project.animalhospital.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws Exception {
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
        ResultSet rst = Util.execute("SELECT emp_id FROM employee order by emp_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String numericPart = lastId.replaceAll("[^0-9]", "");
            if (numericPart.isEmpty()){
                return "EMP001";
            }
            int i = Integer.parseInt(numericPart);
            int newIndex = i+1;
            return String.format("EMP%03d",newIndex);
        }else {
            return "EMP001";
        }
    }

    @Override
    public ArrayList<String> loadId() throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT appointment_id FROM appointments ");
        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()){
            ids.add(rst.getString("appointment_id"));
        }
        return ids;
    }
}
