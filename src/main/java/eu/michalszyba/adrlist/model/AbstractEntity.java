package eu.michalszyba.adrlist.model;

import org.hibernate.type.LocalDateType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity {

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @PrePersist
    public void setCreationDate() {
        if (this.creationDate == null) {
            LocalDateTime currentDate = LocalDateTime.now();
            this.creationDate = currentDate;
            this.modificationDate = currentDate;
        }
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    @PreUpdate
    public void setModificationDate() {
        this.modificationDate = LocalDateTime.now();
    }
}
