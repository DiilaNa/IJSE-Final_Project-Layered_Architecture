package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.ServiceDAO;
import gdse71.project.animalhospital.entity.Service;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public ArrayList<Service> getAll() throws Exception {
        ResultSet rst = Util.execute("select * from service");
        ArrayList<Service> servicedtos = new ArrayList<>();
        while (rst.next()) {
            servicedtos.add(new Service(
               rst.getString(1),
               rst.getString(2),
               rst.getString(3),
               rst.getString(4)
            ));
        }
        return servicedtos;
    }

    @Override
    public boolean save(Service entity) throws Exception {
       return Util.execute("INSERT INTO service VALUES(?,?,?,?)",
               entity.getServiceID(),
               entity.getServiceName(),
               entity.getDuration(),
               entity.getPetIdService()
               );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(Service entity) throws Exception {
        return Util.execute("UPDATE INTO service SET service_name=?,duration=?,petid=? WHERE=service_id=?",
                entity.getServiceName(),
                entity.getDuration(),
                entity.getPetIdService(),
                entity.getServiceID()
                );
    }

    @Override
    public String generateId() throws Exception {
        return "";
    }
}
