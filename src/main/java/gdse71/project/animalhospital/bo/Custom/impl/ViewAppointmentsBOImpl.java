package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.ViewAppointmentsBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.ViewAppointmentsDAO;
import gdse71.project.animalhospital.dto.ViewAppointmentDto;
import gdse71.project.animalhospital.entity.ViewAppointments;

import java.util.ArrayList;

public class ViewAppointmentsBOImpl implements ViewAppointmentsBO {
    ViewAppointmentsDAO viewAppointmentsDAO = (ViewAppointmentsDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.VIEW_APPOINTMENTS);

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
}
