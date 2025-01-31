package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.DocDetailDAO;
import gdse71.project.animalhospital.entity.DocDetails;

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
        return false;
    }

    @Override
    public boolean update(DocDetails dto) throws Exception {
        return false;
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }
}
