package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.SmsBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.SmsDAO;
import gdse71.project.animalhospital.dto.Smsdto;
import gdse71.project.animalhospital.entity.Sms;

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
}
