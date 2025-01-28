package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Smsdto;

import java.util.ArrayList;

public interface SmsBO extends SuperBO {
    ArrayList<Smsdto> getAllSms() throws Exception;
}
