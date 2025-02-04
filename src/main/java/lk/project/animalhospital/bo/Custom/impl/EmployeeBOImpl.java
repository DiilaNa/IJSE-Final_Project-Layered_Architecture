package lk.project.animalhospital.bo.Custom.impl;

import lk.project.animalhospital.bo.Custom.EmployeeBO;
import lk.project.animalhospital.dao.DaoFactory;
import lk.project.animalhospital.dao.custom.DocDetailDAO;
import lk.project.animalhospital.dao.custom.EmployeeDAO;
import lk.project.animalhospital.db.DBConnection;
import lk.project.animalhospital.dto.DocDetailsDto;
import lk.project.animalhospital.dto.Employeedto;
import lk.project.animalhospital.entity.DocDetails;
import lk.project.animalhospital.entity.Employee;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);
    DocDetailDAO docDetailDAO = (DocDetailDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.DOC_DETAIL);

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

    @Override
    public boolean saveEmployee(List<Employeedto>employeedtos, List<DocDetailsDto>docDetailsDtos) throws Exception {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            for (Employeedto employeedto : employeedtos) {
                boolean b1 = employeeDAO.save(new Employee(
                        employeedto.getEmployeeId(),
                        employeedto.getEmployeeName(),
                        employeedto.getEmployeeDuty(),
                        employeedto.getEmployeeAddress(),
                        employeedto.getEmployeePhone()
                ));
                if (!b1) {
                    connection.rollback();
                    return false;
                }
            }
                for (DocDetailsDto docDetailsDto : docDetailsDtos) {
                    boolean b2 = docDetailDAO.save(new DocDetails(
                            docDetailsDto.getEmpid(),
                            docDetailsDto.getAptId()
                    ));
                    if (!b2) {
                        connection.rollback();
                        return false;
                    }
                }
                connection.commit();
                return true;
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employeedto employee) throws Exception {
        return employeeDAO.update(new Employee(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeDuty(),
                employee.getEmployeeAddress(),
                employee.getEmployeePhone()
        ));
    }

    @Override
    public String searchEmployee(String ID) throws Exception {
        return docDetailDAO.searchAppointmentID(ID);
    }

    @Override
    public boolean deleteEmployee(String Employee_id, String Appointment_id) throws Exception {
        Connection connection = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean b2 = docDetailDAO.delete(Appointment_id);
            if (!b2) {
                connection.rollback();
                return false;
            }
            boolean b1 = employeeDAO.delete(Employee_id);
            if (!b1) {
                connection.rollback();
                return false;
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();

            }
            return false;
        }
    }

    @Override
    public String generateEmployeeID() throws Exception {
        return employeeDAO.generateId();
    }

    @Override
    public ArrayList<String> loadEmployeeIDs() throws Exception {
        return employeeDAO.loadId();
    }
}
