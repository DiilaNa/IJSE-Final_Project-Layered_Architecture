package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.Servicedto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceModel {
        public ArrayList<Servicedto> getAllService() throws SQLException, ClassNotFoundException {
            ResultSet rst =  Util.execute("select * from service_booking");

            ArrayList<Servicedto> servicedtos = new ArrayList<>();

            while (rst.next()){
                Servicedto servicedto = new Servicedto(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4)

                );
                servicedtos.add(servicedto);
            }
            return servicedtos;
        }
        public boolean saveService(Servicedto servicedto) throws SQLException, ClassNotFoundException {
            return Util.execute(
                    "insert into service_booking values (?,?,?,?)",
                    servicedto.getServiceID(),
                    servicedto.getServiceName(),
                    servicedto.getDuration(),
                    servicedto.getPetIdService()
            );
        }
        public boolean updateService(Servicedto servicedto) throws SQLException, ClassNotFoundException {
            return Util.execute(
                    "update service_booking set  service_name=?, duration=? , petid=?  where service_id=?",
                    servicedto.getServiceName(),
                    servicedto.getDuration(),
                    servicedto.getPetIdService(),
                    servicedto.getServiceID()
                    );
        }

        public boolean deleteService(String service_id) throws SQLException, ClassNotFoundException {
            return Util.execute("delete from service_booking where service_id=?", service_id);
        }
        public String getNextID() {
            try {
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
            } catch (RuntimeException | ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            return "SVC001";
        }
}
