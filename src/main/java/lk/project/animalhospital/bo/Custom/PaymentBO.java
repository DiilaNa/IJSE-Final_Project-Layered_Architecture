package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.dto.PaymentDto;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
     ArrayList<PaymentDto> getAllPayment() throws Exception;
    boolean deletePayment(String id) throws Exception;
}
