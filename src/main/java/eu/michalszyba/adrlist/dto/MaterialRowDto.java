package eu.michalszyba.adrlist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MaterialRowDto {

    private String unNumber;
    private String unNameAndDescription;
    private Integer quantityPiece;
    private String packagingCode;
    private Long quantityAll;
    private Long deliveryId;
}
