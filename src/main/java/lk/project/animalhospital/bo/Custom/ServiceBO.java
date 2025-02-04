package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.dto.Servicedto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceBO extends SuperBO {
    ArrayList<Servicedto> getAllServices() throws Exception;
    boolean saveService(Servicedto service) throws Exception;
    boolean deleteService(String id) throws Exception;
    boolean updateService(Servicedto service) throws Exception;
    String getServiceId() throws Exception;
    ArrayList<String> getPetIdsComboBox() throws SQLException, ClassNotFoundException;
}
