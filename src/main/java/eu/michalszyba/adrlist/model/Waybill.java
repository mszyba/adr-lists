package eu.michalszyba.adrlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@ToString(exclude = "materialRows")
public class Waybill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * Company
    * */
    private Long companyId;
    private String companyName;
    private String companyAddress;
    private String companyPostcode;
    private String companyCity;
    private String companyCountry;

    /*
    * Customer
    * */
    private Long customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostcode;
    private String customerCity;
    private String customerCountry;

    /*
    * Driver
    * */
    private String driverName;
    private String driverDocument;

    /*
    * Truck
    * */
    private String truckNumber;
    private String truckDescription;

    /*
    * Notes
    * */
    private String descriptionDelivery;

    /*
    * Type of transportation
    * */
    private String transportation;

    /*
    * Type of activities
    * */
    private String typeActivities;

    private Integer pointClass0;
    private Integer pointClass1;
    private Integer pointClass2;
    private Integer pointClass3;
    private Integer pointClass4;
    private Integer points;

    /*
    * Rows of material
    * */
    @OneToMany(mappedBy = "waybill", orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<MaterialRow> materialRows = new ArrayList<>();
}