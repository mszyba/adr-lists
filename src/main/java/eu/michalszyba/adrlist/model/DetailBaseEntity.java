package eu.michalszyba.adrlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class DetailBaseEntity {

    @Email
    private String email;

    private String phone;
    private String personContact;
    private String referenceNo;
    private boolean isActive = true;
}