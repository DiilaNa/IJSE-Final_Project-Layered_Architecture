package gdse71.project.animalhospital.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/animal_hospital", "root", "Ijse@1234");
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException{
        if(dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

}


