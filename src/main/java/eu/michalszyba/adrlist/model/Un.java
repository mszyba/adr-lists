package eu.michalszyba.adrlist.model;

import javax.persistence.*;

@Entity
@Table(name = "un_table")
public class Un {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unNumber;
    private String descriptionUn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnNumber() {
        return unNumber;
    }

    public void setUnNumber(String unNumber) {
        this.unNumber = unNumber;
    }

    public String getDescriptionUn() {
        return descriptionUn;
    }

    public void setDescriptionUn(String descriptionUn) {
        this.descriptionUn = descriptionUn;
    }

    @Override
    public String toString() {
        return "Un{" +
                "id=" + id +
                ", unNumber='" + unNumber + '\'' +
                ", descriptionUn='" + descriptionUn + '\'' +
                '}';
    }
}
