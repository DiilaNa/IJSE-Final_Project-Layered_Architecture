package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Smsdto;

import java.util.ArrayList;

public interface SmsBO extends SuperBO {
    ArrayList<Smsdto> getAllSms() throws Exception;
    boolean saveSms(Smsdto sms) throws Exception;
    boolean updateSms(Smsdto sms) throws Exception;
    boolean deleteSms(String id) throws Exception;
    String getNextSmsNo() throws Exception;
}
