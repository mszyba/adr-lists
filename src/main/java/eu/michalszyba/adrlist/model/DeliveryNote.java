package eu.michalszyba.adrlist.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_notes")
@Where(clause = "is_active = true")
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;
    private Long userId;
    private Long customerId;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private boolean isActive = true;

    @Column(columnDefinition = "json")
    private String deliveryNoteForm;

    public DeliveryNote() {
    }

    @PrePersist
    public void prePersist() {
        if (createdOn == null) {
            createdOn = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customer_id) {
        this.customerId = customer_id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDeliveryNoteForm() {
        return deliveryNoteForm;
    }

    public void setDeliveryNoteForm(String deliveryNoteForm) {
        this.deliveryNoteForm = deliveryNoteForm;
    }

    @Override
    public String toString() {
        return "DeliveryNote{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", userId=" + userId +
                ", customerId=" + customerId +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", isActive=" + isActive +
                ", deliveryNoteForm='" + deliveryNoteForm + '\'' +
                '}';
    }
}
