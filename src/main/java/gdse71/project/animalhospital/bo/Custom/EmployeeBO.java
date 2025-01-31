package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Employeedto;

import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<Employeedto> getALLEmployees() throws Exception;
}
