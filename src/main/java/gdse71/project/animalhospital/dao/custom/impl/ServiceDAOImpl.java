package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.ServiceDAO;
import gdse71.project.animalhospital.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public ArrayList<Service> getAll() throws Exception {
        ResultSet rst = Util.execute("select * from service_booking");
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
       return Util.execute("INSERT INTO service_booking VALUES(?,?,?,?)",
               entity.getServiceID(),
               entity.getServiceName(),
               entity.getDuration(),
               entity.getPetIdService()
               );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return Util.execute("DELETE FROM service_booking WHERE service_id=?",id);
    }

    @Override
    public boolean update(Service entity) throws Exception {
        return Util.execute("UPDATE INTO service SET service_name=?,duration=?,petid=? WHERE service_id=?",
                entity.getServiceName(),
                entity.getDuration(),
                entity.getPetIdService(),
                entity.getServiceID()
                );
    }

    @Override
    public String generateId() throws Exception {
        ResultSet rst = Util.execute("select service_id from service_booking order by service_id desc limit 1");
        if (rst.next()) {
            String lastID = rst.getString(1);
            String numericPart = lastID.replaceAll("[^0-9]", "");
            if (numericPart.isEmpty()) {
                return "SVC001";
            }
            int i = Integer.parseInt(numericPart);
            int newIndex = i + 1;
            return String.format("SVC%03d", newIndex);
        }
        return "SVC001";
    }

    @Override
    public ArrayList<String> loadPetID() throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT pet_id FROM pet");
        ArrayList<String> petids = new ArrayList<>();
        while (rst.next()){
            petids.add(rst.getString("pet_id"));
        }
        return petids;
    }
}
