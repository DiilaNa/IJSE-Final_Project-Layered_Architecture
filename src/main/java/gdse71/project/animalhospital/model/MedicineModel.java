package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.Med_detailDto;
import gdse71.project.animalhospital.dto.MedicineDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineModel {
    public ArrayList<MedicineDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT * from medicine");
        ArrayList<MedicineDto> medicineDtos = new ArrayList<>();
        while (rst.next()) {
            MedicineDto medicineDto = new MedicineDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
            medicineDtos.add(medicineDto);
        }
        return medicineDtos;
    }

    public boolean save(MedicineDto medicineDto, Med_detailDto med_detailDto) throws SQLException, ClassNotFoundException {

        Connection connection = null;

        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medicine (medicine_id, med_name, med_condition, weight) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, medicineDto.getMedicineId());
            preparedStatement.setString(2, medicineDto.getMedicineName());
            preparedStatement.setString(3, medicineDto.getMedicineCondition());
            preparedStatement.setString(4, String.valueOf(medicineDto.getMedicineWeight()));
            preparedStatement.executeUpdate();

            PreparedStatement medDetailstmnt = connection.prepareStatement("INSERT INTO medicine_details(med_id,pet_id) VALUES (?,?)");
            medDetailstmnt.setString(1, med_detailDto.getMeDID());
            medDetailstmnt.setString(2, med_detailDto.getPETID());
            medDetailstmnt.executeUpdate();

            connection.commit();
            return true;

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            return false;

        }
    }

    public boolean update(MedicineDto medicineDto) throws SQLException, ClassNotFoundException {
        boolean medicineUpdated = Util.execute(
                "UPDATE medicine SET med_name = ?, med_condition = ?, weight = ? WHERE medicine_id = ?",
                medicineDto.getMedicineName(),
                medicineDto.getMedicineCondition(),
                medicineDto.getMedicineWeight(),
                medicineDto.getMedicineId()
        );
        return medicineUpdated;
    }



    public boolean delete(String medicineId,String medDetailId) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try{
            connection = Util.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement medidStatement = connection.prepareStatement("DELETE FROM medicine WHERE medicine_id=?");
            medidStatement.setString(1, medicineId);
            medidStatement.executeUpdate();

            PreparedStatement medDetailStmnt = connection.prepareStatement("DELETE FROM medicine_details WHERE med_id=?");
            medDetailStmnt.setString(1, medDetailId);
            medDetailStmnt.executeUpdate();

            connection.commit();
            return true;
        } catch (RuntimeException e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            return false;
        }
      //  return true;
    }
    public String getNextMedid(){
        try{
            ResultSet rst = Util.execute("select medicine_id from medicine order by medicine_id desc limit 1");
            if (rst.next()) {
                String lastID = rst.getString(1);
                String numericPart = lastID.replaceAll("[^0-9]", "");
                if (numericPart.isEmpty()) {
                    return "MED001";
                }
                int i = Integer.parseInt(numericPart);
                int newIndex = i + 1;
                return String.format("MED%03d", newIndex);
            }

        } catch (RuntimeException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "MED001";

    }
}
