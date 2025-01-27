package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.ServiceBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.ServiceDAO;
import gdse71.project.animalhospital.dto.Servicedto;
import gdse71.project.animalhospital.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceBOImpl implements ServiceBO {

    ServiceDAO serviceDAO = (ServiceDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.SERVICE);

    @Override
    public ArrayList<Servicedto> getAllServices() throws Exception {
        ArrayList<Servicedto>servicedtos=new ArrayList<>();
        ArrayList<Service>services=serviceDAO.getAll();
        for(Service service:services) {
            servicedtos.add(new Servicedto(
                    service.getServiceID(),
                    service.getServiceName(),
                    service.getDuration(),
                    service.getPetIdService()
            ));
        }
        return servicedtos;
    }

    @Override
    public boolean saveService(Servicedto service) throws Exception {
        return serviceDAO.save(new Service(
           service.getServiceID(),
           service.getServiceName(),
           service.getDuration(),
           service.getPetIdService()
        ));
    }

    @Override
    public boolean deleteService(String id) throws Exception {
       return serviceDAO.delete(id);
    }

    @Override
    public boolean updateService(Servicedto service) throws Exception {
        return serviceDAO.update(new Service(
                service.getServiceID(),
                service.getServiceName(),
                service.getDuration(),
                service.getPetIdService()
        ));
    }

    @Override
    public String getServiceId() throws Exception {
        return serviceDAO.generateId();
    }

    @Override
    public ArrayList<String> getPetIdsComboBox() throws SQLException, ClassNotFoundException {
        return serviceDAO.loadPetID();
    }
}
