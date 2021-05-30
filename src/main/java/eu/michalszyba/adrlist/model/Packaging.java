package eu.michalszyba.adrlist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Packaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codePackaging;
    private String descriptionPackaging;

    public Packaging() {
    }

    public Long getId() {
        return id;
    }

    public String getCodePackaging() {
        return codePackaging;
    }

    public String getDescriptionPackaging() {
        return descriptionPackaging;
    }

    @Override
    public String toString() {
        return "Packaging{" +
                "id=" + id +
                ", codePackaging='" + codePackaging + '\'' +
                ", descriptionPackaging='" + descriptionPackaging + '\'' +
                '}';
    }
}
