package eu.michalszyba.adrlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Where(clause = "is_active=true")
@Getter @Setter @NoArgsConstructor
@ToString(exclude = {"users", "customers"})
@Embeddable
public class Company extends DetailsBaseEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();
}
