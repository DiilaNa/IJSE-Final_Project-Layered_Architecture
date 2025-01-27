package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Servicedto;

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
