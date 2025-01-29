package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.QuerryDAO;
import gdse71.project.animalhospital.dto.Med_detailDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerryDAOImpl implements QuerryDAO {
    public Med_detailDto findPetDetailsByMedicineId(String medicineId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet  = Util.execute( "SELECT\n" +
                "    m.medicine_id,\n" +
                "    m.med_name,\n" +
                "    m.med_condition,\n" +
                "    m.weight,\n" +
                "    md.pet_id\n" +
                "FROM\n" +
                "    medicine m\n" +
                "        JOIN\n" +
                "    medicine_details md\n" +
                "    ON\n" +
                "        m.medicine_id = md.med_id;" +
                "WHERE med_detail.med_id = ?",medicineId);
        if (resultSet.next()) {
            return new Med_detailDto(
                    resultSet.getString("pet_id"),
                    resultSet.getString(2)
            );
        }
        return null;
    }

}
