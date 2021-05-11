package eu.michalszyba.adrlist.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_company")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_user", nullable = false)
    private String password;

    private String firstName;
    private String lastName;

    @Column(name = "description_user")
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, String description) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
