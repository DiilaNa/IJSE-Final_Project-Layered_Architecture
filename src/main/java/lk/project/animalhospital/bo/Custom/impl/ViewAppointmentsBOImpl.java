package lk.project.animalhospital.bo.Custom.impl;

import lk.project.animalhospital.bo.Custom.ViewAppointmentsBO;
import lk.project.animalhospital.dao.DaoFactory;
import lk.project.animalhospital.dao.custom.QuerryDAO;
import lk.project.animalhospital.dao.custom.ViewAppointmentsDAO;
import lk.project.animalhospital.model.ViewAppointmentDto;
import lk.project.animalhospital.entity.ViewAppointments;

import java.util.ArrayList;

public class ViewAppointmentsBOImpl implements ViewAppointmentsBO {
    ViewAppointmentsDAO viewAppointmentsDAO = (ViewAppointmentsDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.VIEW_APPOINTMENTS);
    QuerryDAO querryDAO = (QuerryDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public ArrayList<ViewAppointmentDto> getAllAppointments() throws Exception {
        ArrayList<ViewAppointmentDto>viewAppointmentDtos = new ArrayList<>();
        ArrayList<ViewAppointments>viewAppointments=viewAppointmentsDAO.getAll();
        for(ViewAppointments viewAppointment:viewAppointments){
            viewAppointmentDtos.add(new ViewAppointmentDto(
                    viewAppointment.getAppointmentId(),
                    viewAppointment.getDate(),
                    viewAppointment.getTime(),
                    viewAppointment.getPetId(),
                    viewAppointment.getPayID(),
                    viewAppointment.getStatus()
            ));
        }
        return viewAppointmentDtos;
    }

    @Override
    public boolean deleteAppointment(String Appointment_id) throws Exception {
        return querryDAO.delete(Appointment_id) ;
/*
        return viewAppointmentsDAO.delete(Appointment_id);
*/
    }
}
