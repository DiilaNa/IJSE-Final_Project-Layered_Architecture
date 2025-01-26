package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.PaymentDao;
import gdse71.project.animalhospital.entity.Payment;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDao {
    @Override
    public ArrayList<Payment> getAll() throws Exception, ClassNotFoundException {
        ResultSet rst = Util.execute("select * from Payment");
        ArrayList<Payment> paymentdtos = new ArrayList<>();
        while (rst.next()) {
            paymentdtos.add(new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return paymentdtos;
    }

    @Override
    public boolean save(Payment entity) throws Exception, ClassNotFoundException {
       return false;
    }

    @Override
    public boolean delete(String id) throws Exception, ClassNotFoundException {
        return Util.execute("DELETE FROM payment WHERE payment_id=?",id);
    }

    @Override
    public boolean update(Payment entity) throws Exception, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateId() throws Exception, ClassNotFoundException {
        return "";
    }
}
