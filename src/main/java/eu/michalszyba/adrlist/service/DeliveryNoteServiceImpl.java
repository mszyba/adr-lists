package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.DeliveryNote;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.repository.DeliveryNoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<DeliveryNote> getAllDeliveriesForCompany() {
        return null;
        //TODO: need to do
    }

    @Override
    public void add(DeliveryNote deliveryNote) {
        deliveryNoteRepository.save(deliveryNote);
    }

    @Override
    public Optional<DeliveryNote> getOneRow(Long id) {
        return deliveryNoteRepository.findById(id);
    }

    //
//    @Override
//    public List<Company> getAllAdrList() {
//        List<Company> companies = new ArrayList<>();
//
//        List<DeliveryNote> allDeliveryNote = deliveryNoteRepository.findAll();
//        for (DeliveryNote deliveryNote : allDeliveryNote) {
////            Company companyA = (Company) new JsonConverter().convertToEntityAttribute(adrListDeliveryNote.getCompany());
////            companies.add(companyA);
//        }
//        return companies;
//    }
}
