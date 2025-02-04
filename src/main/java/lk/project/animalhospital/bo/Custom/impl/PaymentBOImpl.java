package lk.project.animalhospital.bo.Custom.impl;

import lk.project.animalhospital.bo.Custom.PaymentBO;
import lk.project.animalhospital.dao.DaoFactory;
import lk.project.animalhospital.dao.custom.PaymentDao;
import lk.project.animalhospital.dto.PaymentDto;
import lk.project.animalhospital.entity.Payment;

import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDao paymentDao = (PaymentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PAYMENT);
    @Override
    public ArrayList<PaymentDto> getAllPayment() throws Exception {
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        ArrayList<Payment> payments = paymentDao.getAll();
        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto(
                    payment.getPaymentId(),
                    payment.getPaymentDate(),
                    payment.getPaymentMethodd(),
                    payment.getPaymentTime()
            ));
        }
        return paymentDtos;
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDao.delete(id);
    }
}
