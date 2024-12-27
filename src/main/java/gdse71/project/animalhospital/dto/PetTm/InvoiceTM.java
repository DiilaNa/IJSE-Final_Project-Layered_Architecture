package gdse71.project.animalhospital.dto.PetTm;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceTM {
    private String InvoiceNo;
    private String InvoiceName;
    private Double InvoiceAmount;
    private String Paymntid;
    //  private String payID;
}
