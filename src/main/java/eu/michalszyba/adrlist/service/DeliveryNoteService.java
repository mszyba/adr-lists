package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import eu.michalszyba.adrlist.model.DeliveryNote;

import java.util.List;

public interface DeliveryNoteService {

    List<DeliveryNoteForm> getAllDeliveryNotes();

    List<DeliveryNote> getAllDeliveriesForCompany(Long companyId);

    List<DeliveryNoteForm> getAllDeliveriesFormForCompanyId(Long companyId);

    void add(DeliveryNoteForm deliveryNoteForm);
}
