package gdse71.project.animalhospital.bo.Custom.impl;

import gdse71.project.animalhospital.bo.Custom.InvoiceBO;
import gdse71.project.animalhospital.dao.DaoFactory;
import gdse71.project.animalhospital.dao.custom.InvoiceDAO;
import gdse71.project.animalhospital.dto.Invoicedto;
import gdse71.project.animalhospital.dto.PetRecorddto;
import gdse71.project.animalhospital.entity.Invoice;

import java.util.ArrayList;

public class InvoiceBOImpl implements InvoiceBO {

    InvoiceDAO invoiceDAO = (InvoiceDAO) DaoFactory.getInstance().getDao(DaoFactory.DaoType.INVOICE);

    @Override
    public ArrayList<Invoicedto> getAllInvoice() throws Exception {
        ArrayList<Invoicedto> invoicedtos = new ArrayList<>();
        ArrayList<Invoice> invoices = invoiceDAO.getAll();
        for (Invoice invoice : invoices) {
            invoicedtos.add(new Invoicedto(
                    invoice.getInvoiceNo(),
                    invoice.getInvoiceName(),
                    invoice.getInvoiceAmount(),
                    invoice.getPaymntid()
            ));
        }
        return invoicedtos;
    }

    @Override
    public boolean saveInvoice(Invoicedto invoice) throws Exception {
       return invoiceDAO.save(new Invoice(
               invoice.getInvoiceNo(),
               invoice.getInvoiceName(),
               invoice.getInvoiceAmount(),
               invoice.getPaymntid()

       ));
    }

    @Override
    public boolean deleteInvoice(String id) throws Exception {
        return invoiceDAO.delete(id);
    }

    @Override
    public boolean updatePetInvoice(Invoicedto invoice) throws Exception {
        return invoiceDAO.update(new Invoice(
                invoice.getInvoiceNo(),
                invoice.getInvoiceName(),
                invoice.getInvoiceAmount(),
                invoice.getPaymntid()
        ));
    }

    @Override
    public String loadNextInvoiceID() throws Exception {
        return invoiceDAO.generateId();
    }

    @Override
    public ArrayList<String> getAllInvoiceId() throws Exception {
        return invoiceDAO.loadId();
    }
}

