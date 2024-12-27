package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.PaymentDto;
import gdse71.project.animalhospital.dto.Salarydto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  Util.execute("select * from payment");

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        while (rst.next()){
            PaymentDto salarydto = new PaymentDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            );
            paymentDtos.add(salarydto);
        }
        return paymentDtos;
    }
    public boolean save(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "insert into payment values (?,?,?,?)",
                paymentDto.getPaymentId(),
                paymentDto.getPaymentDate(),
                paymentDto.getPaymentMethodd(),
                paymentDto.getPaymentTime()
        );
    }
    public boolean update(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "update payment set  payment_date=?, payment_method=?, payment_time=?  where payment_id=?",
                paymentDto.getPaymentDate(),
                paymentDto.getPaymentMethodd(),
                paymentDto.getPaymentTime(),
                paymentDto.getPaymentId()
                );
    }

    public boolean delete(String payment_id ) throws SQLException, ClassNotFoundException {
        return Util.execute("delete from payment where payment_id=?", payment_id);
    }
}
