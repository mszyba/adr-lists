package eu.michalszyba.adrlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Customer extends AddressBaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}