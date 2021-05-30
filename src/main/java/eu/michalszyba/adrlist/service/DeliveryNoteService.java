package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import eu.michalszyba.adrlist.model.DeliveryNote;

import java.util.List;
import java.util.Optional;

public interface DeliveryNoteService {

    List<DeliveryNote> getAllDeliveryNotes();

    List<DeliveryNote> getAllDeliveriesForCompany(Long companyId);

    List<DeliveryNoteForm> getAllDeliveriesFormForCompanyId(Long companyId);

    void add(DeliveryNote deliveryNote);

    Optional<DeliveryNote> getOneRow(Long id);
}
