package eu.michalszyba.adrlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "material_rows")
@Getter @Setter @NoArgsConstructor
@ToString(exclude = "delivery")
public class MaterialRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long unId;
    private String unNumber;
    private String unNameAndDescription;
    private String unClass;
    private String unLabels;

    private Long packagingId;
    private String packagingCode;
    private String packagingDescription;

    private Integer quantityPiece;
    private Integer quantityAll;

    private Integer quantityPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}