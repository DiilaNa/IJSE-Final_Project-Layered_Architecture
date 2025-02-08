package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.model.DocDetailsDto;
import lk.project.animalhospital.model.Employeedto;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    ArrayList<Employeedto> getALLEmployees() throws Exception;
    boolean saveEmployee(List<Employeedto>employeedtos, List<DocDetailsDto>docDetailsDtos) throws Exception;
    boolean updateEmployee(Employeedto employee) throws Exception;
    String searchEmployee(String ID) throws Exception;
    boolean deleteEmployee(String Employee_id,String Appointment_id) throws Exception;
    String generateEmployeeID() throws Exception;
    ArrayList<String>loadEmployeeIDs() throws Exception;
}
