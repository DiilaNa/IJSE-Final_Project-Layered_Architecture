package gdse71.project.animalhospital.bo.Custom;

import gdse71.project.animalhospital.bo.SuperBO;
import gdse71.project.animalhospital.dto.Invoicedto;
import gdse71.project.animalhospital.dto.PetRecorddto;

import java.util.ArrayList;

public interface InvoiceBO extends SuperBO {
    ArrayList<Invoicedto> getAllInvoice() throws Exception;
    boolean saveInvoice(Invoicedto invoice) throws Exception;
    boolean deleteInvoice(String id) throws Exception;
    boolean updatePetInvoice(Invoicedto invoice) throws Exception;
    String loadNextInvoiceID() throws Exception;
    ArrayList<String> getAllInvoiceId() throws Exception;

}
