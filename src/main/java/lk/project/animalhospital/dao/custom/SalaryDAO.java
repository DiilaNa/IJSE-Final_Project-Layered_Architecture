package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryDAO extends CrudDAO<Salary> {
    ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException;
}
