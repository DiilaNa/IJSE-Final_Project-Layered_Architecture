package lk.project.animalhospital.dao.custom.impl;

import lk.project.animalhospital.dao.Util;
import lk.project.animalhospital.dao.custom.OwnerDao;
import lk.project.animalhospital.entity.Owner;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OwnerDAOImpl implements OwnerDao {
    @Override
    public ArrayList<Owner> getAll() throws Exception, ClassNotFoundException {
        ResultSet rst = Util.execute("select * from owner");

        ArrayList<Owner> owners=new ArrayList<>();

        while (rst.next()) {
            owners.add(new Owner(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return owners;
    }

    @Override
    public boolean save(Owner entity) throws Exception, ClassNotFoundException {
        return Util.execute("INSERT INTO owner VALUES (?, ?, ?, ?)",
                entity.getOwnerId(),
                entity.getOwnerName(),
                entity.getOwnerAddress(),
                entity.getOwnerMail()
        );
    }

    @Override
    public boolean delete(String id) throws Exception, ClassNotFoundException {
        return Util.execute("Delete from owner where owner_Id=?",id);
    }

    @Override
    public boolean update(Owner entity) throws Exception, ClassNotFoundException {
        return Util.execute("UPDATE owner SET name=?, address=?, email=? WHERE owner_id=?",
                entity.getOwnerName(),
                entity.getOwnerAddress(),
                entity.getOwnerMail(),
                entity.getOwnerId()
                );
    }

    @Override
    public String generateId() throws Exception, ClassNotFoundException {
        return "";
    }
}
