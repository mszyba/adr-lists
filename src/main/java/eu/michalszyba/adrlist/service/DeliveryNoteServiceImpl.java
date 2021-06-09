package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.converter.JsonConverter;
import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import eu.michalszyba.adrlist.model.DeliveryNote;
import eu.michalszyba.adrlist.repository.DeliveryNoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    private final DeliveryNoteRepository deliveryNoteRepository;

    public DeliveryNoteServiceImpl(DeliveryNoteRepository deliveryNoteRepository) {
        this.deliveryNoteRepository = deliveryNoteRepository;
    }

    private List<DeliveryNoteForm> getDeliveryNoteForms(List<DeliveryNote> deliveryNotes) {
        List<DeliveryNoteForm> allDeliveryNotesForm = new ArrayList<>();

        for (DeliveryNote s : deliveryNotes) {
            String deliveryNoteFormJson = s.getDeliveryNoteForm();
            DeliveryNoteForm deliveryNoteForm = new JsonConverter().convertToEntityAttribute(deliveryNoteFormJson);
            allDeliveryNotesForm.add(deliveryNoteForm);
        }

        return allDeliveryNotesForm;
    }

    @Override
    public List<DeliveryNoteForm> getAllDeliveryNotes() {
        List<DeliveryNote> deliveryNotes = deliveryNoteRepository.findAll();
        return getDeliveryNoteForms(deliveryNotes);
    }

    @Override
    public List<DeliveryNote> getAllDeliveriesForCompany(Long companyId) {
        return deliveryNoteRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<DeliveryNoteForm> getAllDeliveriesFormForCompanyId(Long companyId) {
        List<DeliveryNote> allByCompanyId = deliveryNoteRepository.findAllByCompanyId(companyId);
        return getDeliveryNoteForms(allByCompanyId);
    }

    @Override
    public void add(DeliveryNoteForm deliveryNoteForm) {
        DeliveryNote deliveryNote = new DeliveryNote();
        Long companyId = deliveryNoteForm.getCompany().getId();
        Long customerId = deliveryNoteForm.getCustomer().getId();
        String columnDeliveryNoteForm = new JsonConverter().convertToDatabaseColumn(deliveryNoteForm);

        deliveryNote.setCompanyId(companyId);
        deliveryNote.setCustomerId(customerId);
        deliveryNote.setDeliveryNoteForm(columnDeliveryNoteForm);
        deliveryNoteRepository.save(deliveryNote);
    }
}
