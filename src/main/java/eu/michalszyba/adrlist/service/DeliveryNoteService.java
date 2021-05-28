package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.DeliveryNote;
import eu.michalszyba.adrlist.model.Company;

import java.util.List;
import java.util.Optional;

public interface DeliveryNoteService {

    List<DeliveryNote> getAllDeliveryNotes();

    List<DeliveryNote> getAllDeliveriesForCompany();

    void add(DeliveryNote deliveryNote);

    Optional<DeliveryNote> getOneRow(Long id);
}
