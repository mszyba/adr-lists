package eu.michalszyba.adrlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 250)
    @NotBlank
    private String customerName;

    @NotBlank
    private String address;

    @NotBlank
    private String postCode;

    @NotBlank
    private String city;

    private String phone;

    @Email
    private String email;
    private String personContact;
    private String referenceNo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonContact() {
        return personContact;
    }

    public void setPersonContact(String personContact) {
        this.personContact = personContact;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", personContact='" + personContact + '\'' +
                ", referenceNo='" + referenceNo + '\'' +
                '}';
    }
}
