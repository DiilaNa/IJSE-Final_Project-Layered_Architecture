package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.EmployeeBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.EmployeeDAO;
import gdse71.project.animalhospital.dto.Employeedto;
import gdse71.project.animalhospital.entity.Employee;

import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);

    @Override
    public ArrayList<Employeedto> getALLEmployees() throws Exception {
        ArrayList<Employeedto> employeedtos = new ArrayList<>();
        ArrayList<Employee> employees = employeeDAO.getAll();

        for (Employee employee : employees) {
            employeedtos.add(new Employeedto(
                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getEmployeeDuty(),
                    employee.getEmployeeAddress(),
                    employee.getEmployeePhone()
                    ));
        }
        return employeedtos;
    }
}
