package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.PaymentDto;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
     ArrayList<PaymentDto> getAllPayment() throws Exception;
    boolean deletePayment(String id) throws Exception;
}
