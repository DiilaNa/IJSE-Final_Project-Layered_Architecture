package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee> {
    ArrayList<String> loadId () throws SQLException, ClassNotFoundException;
}
