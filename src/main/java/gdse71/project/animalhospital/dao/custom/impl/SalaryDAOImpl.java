package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.SalaryDAO;
import gdse71.project.animalhospital.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<Salary> getAll() throws Exception {
        ResultSet rst = Util.execute("select * from salary");
        ArrayList<Salary> salarydtos = new ArrayList<>();
        while(rst.next()){
            salarydtos.add(new Salary(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getDouble(3),
                    rst.getString(4)
            ));
        }
        return salarydtos;
    }

    @Override
    public boolean save(Salary entity) throws Exception {
        return Util.execute("INSERT INTO salary VALUES(?,?,?,?)",
                entity.getSalaryId(),
                entity.getDate(),
                entity.getAmount(),
                entity.getEmployeeId()
                );
    }

    @Override
    public boolean delete(String id) throws Exception {
       return Util.execute("DELETE FROM salary WHERE salary_id = ?",id);
    }

    @Override
    public boolean update(Salary entity) throws Exception {
       return Util.execute("UPDATE salary set payment_date=?,amount=?,employee_id=? WHERE salary_id=?",
               entity.getDate(),
               entity.getAmount(),
               entity.getEmployeeId(),
               entity.getSalaryId()
               );
    }

    @Override
    public String generateId() throws Exception {
        ResultSet rst = Util.execute("SELECT salary_id from salary order by salary_id desc limit 1");

        if (rst.next()) { // If there is a result
            String lastId = rst.getString(1); // Get the latest ID
            String numericPart = lastId.replaceAll("[^0-9]", ""); // Extract numeric part
            int i = Integer.parseInt(numericPart); // Convert numeric part to integer
            int newIdIndex = i + 1; // Increment ID
            return String.format("SAL%03d", newIdIndex); // Format ID with leading zeros
        } else {
            // No existing IDs, start with default
            return "SAL001";
        }
    }

    @Override
    public ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("select emp_id from employee");
        ArrayList<String> employeeids = new ArrayList<>();

        while (rst.next()){
            employeeids.add(rst.getString("emp_id"));

        }
        return employeeids;
    }
}
