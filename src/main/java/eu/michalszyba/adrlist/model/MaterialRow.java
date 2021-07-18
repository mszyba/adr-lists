package eu.michalszyba.adrlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "material_rows")
@Getter @Setter @NoArgsConstructor
public class MaterialRow extends UnBaseEntity {

    private String packagingCode;
    private Integer quantityPiece;
    private Integer quantityAll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}