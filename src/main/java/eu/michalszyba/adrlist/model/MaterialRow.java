package eu.michalszyba.adrlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "material_rows")
@Getter @Setter @NoArgsConstructor
public class MaterialRow extends UnBaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;


//    private String unNumber;
//    private String unNameAndDescription;

    private Long quantityPiece;
    private String packagingCode;
    private Long quantityAll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}
