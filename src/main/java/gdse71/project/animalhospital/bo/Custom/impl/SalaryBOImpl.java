package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.SalaryBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.SalaryDAO;
import gdse71.project.animalhospital.dto.Salarydto;
import gdse71.project.animalhospital.entity.Salary;

import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryDAO = (SalaryDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.SALARY);

    @Override
    public ArrayList<Salarydto> getAllSalary() throws Exception {
        ArrayList<Salarydto> salarydtos = new ArrayList<>();
        ArrayList<Salary> salaries = salaryDAO.getAll();
        for (Salary salary : salaries) {
            salarydtos.add(new Salarydto(
                    salary.getSalaryId(),
                    salary.getDate(),
                    salary.getAmount(),
                    salary.getEmployeeId()
            ));
        }
        return salarydtos;
    }

    @Override
    public boolean saveSalary(Salarydto salary) throws Exception {
        return salaryDAO.save(new Salary(
                salary.getSalaryId(),
                salary.getDate(),
                salary.getAmount(),
                salary.getEmployeeId()
        ));
    }

    @Override
    public boolean updateSalary(Salarydto salary) throws Exception {
        return  salaryDAO.update(new Salary(
                salary.getSalaryId(),
                salary.getDate(),
                salary.getAmount(),
                salary.getEmployeeId()
        ));
    }

    @Override
    public boolean deleteSalary(String id) throws Exception {
        return salaryDAO.delete(id);
    }

    @Override
    public String loadNextSalaryId() throws Exception {
        return salaryDAO.generateId();
    }

    @Override
    public ArrayList<String> getEmployeeIds() throws Exception {
        return salaryDAO.loadEmployeeIds();
    }
}
