package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.converter.JsonConverter;
import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import eu.michalszyba.adrlist.model.DeliveryNote;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.repository.DeliveryNoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    private final DeliveryNoteRepository deliveryNoteRepository;

    public DeliveryNoteServiceImpl(DeliveryNoteRepository deliveryNoteRepository) {
        this.deliveryNoteRepository = deliveryNoteRepository;
    }

    @Override
    public List<DeliveryNote> getAllDeliveryNotes() {
        return deliveryNoteRepository.findAll();
    }

    @Override
    public List<DeliveryNote> getAllDeliveriesForCompany(Long companyId) {
        List<DeliveryNote> allByCompanyId = deliveryNoteRepository.findAllByCompanyId(companyId);


        return deliveryNoteRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<DeliveryNoteForm> getAllDeliveriesFormForCompanyId(Long companyId) {
        List<DeliveryNote> allByCompanyId = deliveryNoteRepository.findAllByCompanyId(companyId);
        List<DeliveryNoteForm> allDeliveryNotesForm = new ArrayList<>();

        // TODO: zmieć docelowo na stream?
        for (DeliveryNote s : allByCompanyId) {
            String deliveryNoteFormJson = s.getDeliveryNoteForm();
            DeliveryNoteForm deliveryNoteForm = new JsonConverter().convertToEntityAttribute(deliveryNoteFormJson);
            allDeliveryNotesForm.add(deliveryNoteForm);
        }

        return allDeliveryNotesForm;
    }

    @Override
    public void add(DeliveryNoteForm deliveryNoteForm) {
        DeliveryNote deliveryNote = new DeliveryNote();
        Long company_id = deliveryNoteForm.getCompany().getId();
        Long customer_id = deliveryNoteForm.getCustomer().getId();
        String columnDeliveryNoteForm = new JsonConverter().convertToDatabaseColumn(deliveryNoteForm);

        deliveryNote.setCompanyId(company_id);
        deliveryNote.setCustomerId(customer_id);
        deliveryNote.setDeliveryNoteForm(columnDeliveryNoteForm);
        deliveryNoteRepository.save(deliveryNote);
    }

    @Override
    public Optional<DeliveryNote> getOneRow(Long id) {
        return deliveryNoteRepository.findById(id);
    }

}
