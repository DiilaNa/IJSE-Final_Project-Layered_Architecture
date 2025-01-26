package gdse71.project.animalhospital.dao.custom;

import gdse71.project.animalhospital.dao.CrudDAO;
import gdse71.project.animalhospital.entity.Invoice;

import java.util.ArrayList;

public interface InvoiceDAO extends CrudDAO<Invoice> {
    ArrayList<String> loadId() throws Exception;
}
