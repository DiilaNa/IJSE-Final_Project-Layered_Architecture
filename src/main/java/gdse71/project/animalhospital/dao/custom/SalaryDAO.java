package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryDAO extends CrudDAO<Salary> {
    ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException;
}
