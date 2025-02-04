package lk.project.animalhospital.bo.Custom.impl;

import lk.project.animalhospital.bo.Custom.SmsBO;
import lk.project.animalhospital.dao.DaoFactory;
import lk.project.animalhospital.dao.custom.SmsDAO;
import lk.project.animalhospital.dto.Smsdto;
import lk.project.animalhospital.entity.Sms;

import java.util.ArrayList;

public class SmsBOImpl implements SmsBO {

    SmsDAO smsDAO = (SmsDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.SMS);
    @Override
    public ArrayList<Smsdto> getAllSms() throws Exception {
        ArrayList<Smsdto> smsdtos = new ArrayList<>();
        ArrayList<Sms> sms1= smsDAO.getAll();
        for (Sms sms : sms1) {
            smsdtos.add(new Smsdto(
                    sms.getSmsNo(),
                    sms.getDate(),
                    sms.getStatus(),
                    sms.getAppID()
            ));
        }
        return smsdtos;
    }

    @Override
    public boolean saveSms(Smsdto sms) throws Exception {
        return smsDAO.save(new Sms(sms.getSmsNo(), sms.getDate(), sms.getStatus(), sms.getAppID()));
    }

    @Override
    public boolean updateSms(Smsdto sms) throws Exception {
        return  smsDAO.update(new Sms(sms.getSmsNo(), sms.getDate(), sms.getStatus(), sms.getAppID()));
    }

    @Override
    public boolean deleteSms(String id) throws Exception {
        return smsDAO.delete(id);
    }

    @Override
    public String getNextSmsNo() throws Exception {
        return smsDAO.generateId();
    }

    @Override
    public ArrayList<String> getEmailList() throws Exception {
        return smsDAO.loadSmsComboBox();
    }

    @Override
    public ArrayList<String> getAppointmentId() throws Exception {
        return  smsDAO.loadAppointmentIdComboBox();
    }
}
