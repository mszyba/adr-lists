package eu.michalszyba.adrlist.model;

import org.hibernate.type.LocalDateType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity {

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    @PrePersist
    protected void onCreate() {

        if (this.creationDate == null) {
            Date currentDate = new Date();
            this.creationDate = currentDate;
            this.modificationDate = currentDate;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.modificationDate = new Date();
    }

}
