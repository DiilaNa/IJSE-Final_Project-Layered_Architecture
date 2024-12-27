package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.Ownerdto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OwnerModel {

    public ArrayList<Ownerdto> getAllOwner() throws SQLException, ClassNotFoundException {
    ResultSet rst =  Util.execute("select * from owner");

    ArrayList<Ownerdto> ownerdtos = new ArrayList<>();

    while (rst.next()){
        Ownerdto ownerdto = new Ownerdto(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4)
        );
        ownerdtos.add(ownerdto);
    }
    return ownerdtos;
}
    public boolean saveOwner(Ownerdto ownerdto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "insert into owner values (?,?,?,?)",
                ownerdto.getOwnerId(),
                ownerdto.getOwnerName(),
                ownerdto.getOwnerAddress(),
                ownerdto.getOwnerMail()
        );
    }
    public boolean updateOwner(Ownerdto ownerdto) throws SQLException, ClassNotFoundException {
        return Util.execute(
                "update owner set  name=?, address=?, email=? where owner_id=?",
                ownerdto.getOwnerName(),
                ownerdto.getOwnerAddress(),
                ownerdto.getOwnerMail(),
                ownerdto.getOwnerId()
                );
    }
    public boolean deleteOwner(String ownerid) throws SQLException, ClassNotFoundException {
        return Util.execute("delete from owner where owner_id=?", ownerid);
    }

}
