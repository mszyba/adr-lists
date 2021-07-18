package eu.michalszyba.adrlist.form;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class DeliveryNoteForm {

    private Company company;
    private Customer customer;

    @Size(min = 2, max = 30)
    private String driverName;

    @Size(min = 2, max = 15)
    private String truckNumber;

    private String descriptionDelivery;
    private List<UnForm> unForms = new ArrayList<>();
}
