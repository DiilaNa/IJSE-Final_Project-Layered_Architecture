package gdse71.project.animalhospital.CrudUtil;



import gdse71.project.animalhospital.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
    public static <T>T execute(String sql,Object... obj) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        for (int i=0;i<obj.length;i++){
            pst.setObject((i+1),obj[i]);
        }

        if (sql.startsWith("select") || sql.startsWith("SELECT")){

            ResultSet resultSet = pst.executeQuery();
            return (T) resultSet;

        }else {
            int i = pst.executeUpdate();
            boolean isSaved = i >0;
            return (T) ((Boolean) isSaved);
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection();
    }
}