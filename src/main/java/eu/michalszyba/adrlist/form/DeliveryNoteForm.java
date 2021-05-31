package eu.michalszyba.adrlist.form;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Customer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DeliveryNoteForm {

    private Company company;
    private Customer customer;

    @Size(min = 2, max = 30)
    private String driverName;

    @Size(min = 2, max = 15)
    private String truckNumber;

    private String descriptionDelivery;
    private List<UnForm> unForms = new ArrayList<>();

    public DeliveryNoteForm() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getDescriptionDelivery() {
        return descriptionDelivery;
    }

    public void setDescriptionDelivery(String descriptionDelivery) {
        this.descriptionDelivery = descriptionDelivery;
    }

    public List<UnForm> getUnForms() {
        return unForms;
    }

    public void setUnForms(List<UnForm> unForms) {
        this.unForms = unForms;
    }

    @Override
    public String toString() {
        return "DeliveryNoteForm{" +
                "company=" + company +
                ", customer=" + customer +
                ", driverName='" + driverName + '\'' +
                ", truckNumber='" + truckNumber + '\'' +
                ", descriptionDelivery='" + descriptionDelivery + '\'' +
                ", unForms=" + unForms +
                '}';
    }
}
