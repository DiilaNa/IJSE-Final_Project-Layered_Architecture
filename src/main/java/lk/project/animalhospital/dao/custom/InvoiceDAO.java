package lk.project.animalhospital.dao.custom;

import lk.project.animalhospital.dao.CrudDAO;
import lk.project.animalhospital.entity.Invoice;

import java.util.ArrayList;

public interface InvoiceDAO extends CrudDAO<Invoice> {
    ArrayList<String> loadId() throws Exception;
}
