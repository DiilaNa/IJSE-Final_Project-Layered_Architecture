package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.SmsDAO;
import gdse71.project.animalhospital.entity.Sms;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SmsDAOImpl implements SmsDAO {
    @Override
    public ArrayList<Sms> getAll() throws Exception {
        ResultSet rst = Util.execute("select * from mail_reminder");
        ArrayList<Sms> smsdtos = new ArrayList<>();
        while (rst.next()) {
            smsdtos.add(new Sms(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return smsdtos;
    }

    @Override
    public boolean save(Sms entity) throws Exception {
        return Util.execute("insert into mail_reminder values(?,?,?,?)",
                entity.getSmsNo(),
                entity.getDate(),
                entity.getStatus(),
                entity.getAppID()
                );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return Util.execute("DELETE FROM mail_reminder WHERE mail_no=?",id);
    }

    @Override
    public boolean update(Sms entity) throws Exception {
       return Util.execute("UPDATE mail_reminder SET mail_date=?, status=?, app_id=? WHERE mail_no=? ",
               entity.getDate(),
               entity.getStatus(),
               entity.getAppID(),
               entity.getSmsNo()
       );
    }

    @Override
    public String generateId() throws Exception {
        ResultSet rst = Util.execute("SELECT mail_no from mail_reminder order by mail_no desc limit 1");

        if (rst.next()) {
          String lastId = rst.getString(1);
          String numericPart = lastId.replaceAll("[^0-9]", "");
          if (numericPart.isEmpty()){
              return "SMS001";
          }
          int i = Integer.parseInt(numericPart);
          int newIndex = i + 1;
            return String.format("SMS%03d", newIndex); // Return the new customer ID in format Cnnn
        }
        return "SMS001";
    }
}
