package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Salarydto;

import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    ArrayList<Salarydto> getAllSalary() throws Exception;
    boolean saveSalary(Salarydto salary) throws Exception;
    boolean updateSalary(Salarydto salary) throws Exception;
    boolean deleteSalary(String id) throws Exception;
    String loadNextSalaryId() throws Exception;

}
