package gdse71.project.animalhospital.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Invoicedto {
    private String InvoiceNo;
    private String InvoiceName;
    private Double InvoiceAmount;
    private String Paymntid;
}
