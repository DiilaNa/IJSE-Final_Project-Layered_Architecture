package gdse71.project.animalhospital.dao.custom.impl;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.dao.custom.InvoiceDAO;
import gdse71.project.animalhospital.entity.Invoice;

import java.sql.ResultSet;
import java.util.ArrayList;

public class InvoiceDAOImpl implements InvoiceDAO {
    @Override
    public ArrayList<Invoice> getAll() throws Exception, ClassNotFoundException {
        ResultSet rst = Util.execute("select * from Invoice");
        ArrayList<Invoice> invoicedtos = new ArrayList<>();
        while (rst.next()) {
            invoicedtos.add(new Invoice(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4)));
        }
        return invoicedtos;
    }

    @Override
    public boolean save(Invoice entity) throws Exception, ClassNotFoundException {
        return Util.execute("INSERT INTO invoice VALUES(?,?,?,?)",
                entity.getInvoiceNo(),
                entity.getInvoiceName(),
                entity.getInvoiceAmount(),
                entity.getPaymntid()
        );
    }

    @Override
    public boolean delete(String id) throws Exception, ClassNotFoundException {
        return Util.execute("DELETE FROM invoice WHERE invoice_no=?", id);
    }

    @Override
    public boolean update(Invoice entity) throws Exception, ClassNotFoundException {
        return Util.execute("UPDATE invoice SET name=?,amount=?,pay_id=? WHERE invoice_no=?",
                entity.getInvoiceName(),
                entity.getInvoiceAmount(),
                entity.getPaymntid(),
                entity.getInvoiceNo()

        );
    }

    @Override
    public String generateId() throws Exception, ClassNotFoundException {
        // Fetch the latest invoice_no from the database
        ResultSet rst = Util.execute("SELECT invoice_no FROM invoice ORDER BY invoice_no DESC LIMIT 1");

        if (rst.next()) { // If there is a result
            String lastId = rst.getString(1); // Get the latest ID
            String numericPart = lastId.replaceAll("[^0-9]", ""); // Extract numeric part

            // Handle edge case: no numeric part found
            if (numericPart.isEmpty()) {
                return "INV001";
            }

            int i = Integer.parseInt(numericPart); // Convert numeric part to integer
            int newIdIndex = i + 1; // Increment ID
            return String.format("INV%03d", newIdIndex); // Format ID with leading zeros
        } else {
            // No existing IDs, start with default
            return "INV001";
        }
    }

    @Override
    public ArrayList<String> loadId() throws Exception {
        ResultSet rst = Util.execute("SELECT payment_id FROM payment");
        ArrayList<String> petIds = new ArrayList<>();

        while (rst.next()) {
            petIds.add(rst.getString("payment_id"));
        }

        return petIds;
    }
}





