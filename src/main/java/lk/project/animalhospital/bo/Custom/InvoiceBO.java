package lk.project.animalhospital.bo.Custom;

import lk.project.animalhospital.bo.SuperBO;
import lk.project.animalhospital.dto.Invoicedto;

import java.util.ArrayList;

public interface InvoiceBO extends SuperBO {
    ArrayList<Invoicedto> getAllInvoice() throws Exception;
    boolean saveInvoice(Invoicedto invoice) throws Exception;
    boolean deleteInvoice(String id) throws Exception;
    boolean updatePetInvoice(Invoicedto invoice) throws Exception;
    String loadNextInvoiceID() throws Exception;
    ArrayList<String> getAllInvoiceId() throws Exception;

}
