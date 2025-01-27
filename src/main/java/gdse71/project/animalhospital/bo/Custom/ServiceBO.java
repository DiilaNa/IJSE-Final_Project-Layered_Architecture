package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Servicedto;

import java.util.ArrayList;

public interface ServiceBO extends SuperBO {
    ArrayList<Servicedto> getAllServices() throws Exception;
    boolean saveService(Servicedto service) throws Exception;
    boolean deleteService(String id);
    boolean updateService(Servicedto service);
    ArrayList<String> getServiceid();
}
