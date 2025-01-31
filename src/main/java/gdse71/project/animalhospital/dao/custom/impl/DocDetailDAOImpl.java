package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.DocDetailDAO;
import gdse71.project.animalhospital.entity.DocDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocDetailDAOImpl implements DocDetailDAO {

    @Override
    public ArrayList<DocDetails> getAll() throws Exception {
        return null;
    }
    @Override
    public boolean save(DocDetails entity) throws Exception {
        return Util.execute("INSERT INTO doc_details VALUES(?,?)",
                entity.getEmpid(),
                entity.getAptId()
                );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return Util.execute("DELETE FROM doc_details WHERE appoint_id=? ",id);
    }

    @Override
    public boolean update(DocDetails dto) throws Exception {
        return false;
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }

    @Override
    public String searchAppointmentID(String ID) throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT appoint_id from doc_details WHERE emp_id=? ",ID);
        if (rst.next()){
            return rst.getString("appoint_id");
        }
        return "NO NAME FOUND";

    }
}
