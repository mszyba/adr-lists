package eu.michalszyba.adrlist.model;

import javax.persistence.*;

@Entity
@Table(name = "adr_lists_entity")
public class AdrListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "json")
    private String company;

    @Column(columnDefinition = "json")
    private String customer;

    private String firstName;
    private String lastName;

    @Column(columnDefinition = "json")
    private String unRows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
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

    public String getUnRows() {
        return unRows;
    }

    public void setUnRows(String unRows) {
        this.unRows = unRows;
    }
}
