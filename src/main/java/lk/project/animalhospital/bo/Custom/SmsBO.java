package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.model.Smsdto;

import java.util.ArrayList;

public interface SmsBO extends SuperBO {
    ArrayList<Smsdto> getAllSms() throws Exception;
    boolean saveSms(Smsdto sms) throws Exception;
    boolean updateSms(Smsdto sms) throws Exception;
    boolean deleteSms(String id) throws Exception;
    String getNextSmsNo() throws Exception;
    ArrayList<String> getEmailList() throws Exception;
    ArrayList<String> getAppointmentId() throws Exception;
}
