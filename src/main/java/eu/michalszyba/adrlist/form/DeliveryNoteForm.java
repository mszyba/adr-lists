package eu.michalszyba.adrlist.form;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DeliveryNoteForm {

    private Company company;
    private Customer customer;
    private String firstName;
    private String lastName;
    private List<UnForm> unForms = new ArrayList<>();

    public DeliveryNoteForm() {
    }

    @Override
    public String toString() {
        return "MainForm{" +
                "company=" + company +
                ", customer=" + customer +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", unForms=" + unForms +
                '}';
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UnForm> getUnForms() {
        return unForms;
    }

    public void setUnForms(List<UnForm> unForms) {
        this.unForms = unForms;
    }
}
