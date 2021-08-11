package eu.michalszyba.adrlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class AddressBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Size(min = 2, max = 250)
    @NotBlank
    protected String name;

    @NotBlank
    protected String address;

    @NotBlank
    protected String postcode;

    @NotBlank
    protected String city;

    @NotBlank
    protected String country;

    @Email
    protected String email;

    protected String phone;
    protected String personContact;
    protected String referenceNo;
    protected boolean isActive = true;
}