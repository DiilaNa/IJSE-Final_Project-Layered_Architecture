package gdse71.project.animalhospital.model;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dto.Invoicedto;
import gdse71.project.animalhospital.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceModel {
    public ArrayList<Invoicedto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = Util.execute("SELECT * FROM invoice");

        ArrayList<Invoicedto> invoicedtos = new ArrayList<>();
        while (rst.next()) {
            Invoicedto invoicedto = new Invoicedto(
                    rst.getString(1), // invoice_no
                    rst.getString(2), // invoice_name
                    rst.getDouble(3),
                    rst.getString(4)// invoice_amount
            );
            invoicedtos.add(invoicedto);
        }
        return invoicedtos;
    }

    public boolean save(Invoicedto invoicedto) throws SQLException, ClassNotFoundException {

        // Insert into medicine table
                return Util.execute(
                "INSERT INTO invoice (invoice_no, name, amount, pay_id) VALUES (?, ?, ?, ?)",
                invoicedto.getInvoiceNo(),
                invoicedto.getInvoiceName(),
                invoicedto.getInvoiceAmount(),
                invoicedto.getPaymntid()
        );
    }

    public boolean update(Invoicedto invoicedto) throws SQLException, ClassNotFoundException {
        // Update invoice table
        return Util.execute(
                "UPDATE invoice SET name = ?, amount = ?, pay_id = ? WHERE invoice_no = ?",
                invoicedto.getInvoiceName(),
                invoicedto.getInvoiceAmount(),
                invoicedto.getPaymntid(),
                invoicedto.getInvoiceNo()
        );
    }


    public boolean delete(String invoice_no) throws SQLException, ClassNotFoundException {
            return Util.execute("DELETE FROM invoice WHERE invoice_no = ?", invoice_no);
    }
    public String getNextInvoiceId(){
        try {
            ResultSet rst = null;
            rst = Util.execute("select invoice_no from invoice order by invoice_no desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1);
                String numericPart = lastId.replaceAll("[^0-9]", "");
                if (numericPart.isEmpty()) {
                    return "INV001";
                }
                int i = Integer.parseInt(numericPart);
                int newIdIndex = i + 1;
                return String.format("INV%03d", newIdIndex);
            }
        } catch (ClassNotFoundException | NumberFormatException |SQLException e) {
            throw new RuntimeException(e);
        }
        return "INV001";
    }

  /*  public Double getServicePrice() {
        Double servicePrice = null;
        String query = "SELECT ";
        try (PreparedStatement stmt = DBConnection.prepareStatement(query)) {
            stmt.setInt(1, serviceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                servicePrice = rs.getDouble("service_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicePrice;

    }*/
}
