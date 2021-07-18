package eu.michalszyba.adrlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "deliveries")
@Getter @Setter @NoArgsConstructor
@ToString(exclude = "materialRows")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;
    private String companyName;
    private String companyAddress;
    private Long customerId;
    private String customerName;
    private String customerAddress;

    private String driverName;
    private String truckNumber;
    private String descriptionDelivery;

    @OneToMany(mappedBy = "delivery", orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<MaterialRow> materialRows = new ArrayList<>();
}
