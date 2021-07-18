package eu.michalszyba.adrlist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DeliveryDto {

    private Long companyId;
    private String companyName;
    private String companyAddress;
    private Long customerId;
    private String customerName;
    private String customerAddress;
}
