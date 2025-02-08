package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.model.Salarydto;

import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    ArrayList<Salarydto> getAllSalary() throws Exception;
    boolean saveSalary(Salarydto salary) throws Exception;
    boolean updateSalary(Salarydto salary) throws Exception;
    boolean deleteSalary(String id) throws Exception;
    String loadNextSalaryId() throws Exception;
    ArrayList<String> getEmployeeIds() throws Exception;

}
