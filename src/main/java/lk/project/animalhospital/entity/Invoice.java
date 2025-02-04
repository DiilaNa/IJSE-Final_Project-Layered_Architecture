package lk.project.animalhospital.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Invoice {
    private String InvoiceNo;
    private String InvoiceName;
    private Double InvoiceAmount;
    private String Paymntid;
}
