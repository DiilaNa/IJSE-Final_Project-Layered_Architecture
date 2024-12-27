package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.Docdto;
import gdse71.project.animalhospital.dto.Employeedto;
import gdse71.project.animalhospital.dto.Ownerdto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public ArrayList<Employeedto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  Util.execute("select * from employee");

        ArrayList<Employeedto> employeedtos = new ArrayList<>();

        while (rst.next()){
            Employeedto employeedto = new Employeedto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)

            );
            employeedtos.add(employeedto);
        }
        return employeedtos;
    }
    public boolean save(Employeedto employeedto, Docdto docdto) throws SQLException, ClassNotFoundException {
        Connection connection =null;
        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement employstmnt = connection.prepareStatement("INSERT INTO employee(emp_id,emp_name,duty,address,tel_no) VALUES (?,?,?,?,?)");
            employstmnt.setString(1, employeedto.getEmployeeId());
            employstmnt.setString(2, employeedto.getEmployeeName());
            employstmnt.setString(3, employeedto.getEmployeeDuty());
            employstmnt.setString(4, employeedto.getEmployeeAddress());
            employstmnt.setString(5, employeedto.getEmployeePhone());
            employstmnt.execute();

            PreparedStatement docstmnt = connection.prepareStatement("INSERT INTO doc_details (emp_id,appoint_id)VALUES (?,?)");
            docstmnt.setString(1, docdto.getEmpid());
            docstmnt.setString(2, docdto.getAptId());
            docstmnt.execute();

            connection.commit();
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();

            }
            e.printStackTrace();
            return false;
        }

    }
    public boolean update(Employeedto employeedto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "update employee set emp_name=?, duty=?, address=?, tel_no=? where emp_id=?",
                employeedto.getEmployeeName(),
                employeedto.getEmployeeDuty(),
                employeedto.getEmployeeAddress(),
                employeedto.getEmployeePhone(),
                employeedto.getEmployeeId()
        );
    }

    public boolean delete(String emp_id,String apt_id) throws SQLException, ClassNotFoundException {
        Connection connection =null;

        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement empStmnt = connection.prepareStatement("delete from employee where emp_id=?");
            empStmnt.setString(1, emp_id);
            empStmnt.execute();

            PreparedStatement aptStmnt = connection.prepareStatement("delete from doc_details where appoint_id=?");
            aptStmnt.setString(1, apt_id);
            aptStmnt.execute();

            connection.commit();
            return true;

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            return false;
        }

    }
    public String getNextID(){
        try {
            ResultSet rst = null;
            rst = Util.execute("select emp_id from employee order by emp_id desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1);
                String numericPart = lastId.replaceAll("[^0-9]", "");
                if (numericPart.isEmpty()) {
                    return "EMP001";
                }
                int i = Integer.parseInt(numericPart);
                int newIdIndex = i + 1;
                return String.format("EMP%03d", newIdIndex);
            }
        } catch (ClassNotFoundException | NumberFormatException |SQLException e) {
            throw new RuntimeException(e);
        }
        return "EMP001";
    }
}
