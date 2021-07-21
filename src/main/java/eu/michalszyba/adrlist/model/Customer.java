package eu.michalszyba.adrlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
@Getter @Setter @NoArgsConstructor
public class Customer extends DetailsBaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}