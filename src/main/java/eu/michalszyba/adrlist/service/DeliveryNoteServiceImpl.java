package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.converter.JsonConverter;
import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import eu.michalszyba.adrlist.form.UnForm;
import eu.michalszyba.adrlist.model.Delivery;
import eu.michalszyba.adrlist.model.DeliveryNote;
import eu.michalszyba.adrlist.model.MaterialRow;
import eu.michalszyba.adrlist.repository.DeliveryNoteRepository;
import eu.michalszyba.adrlist.repository.DeliveryRepository;
import eu.michalszyba.adrlist.repository.MaterialRowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    private final DeliveryNoteRepository deliveryNoteRepository;
    private final DeliveryRepository deliveryRepository;
    private final MaterialRowRepository materialRowRepository;

    public DeliveryNoteServiceImpl(DeliveryNoteRepository deliveryNoteRepository, DeliveryRepository deliveryRepository, MaterialRowRepository materialRowRepository) {
        this.deliveryNoteRepository = deliveryNoteRepository;
        this.deliveryRepository = deliveryRepository;
        this.materialRowRepository = materialRowRepository;
    }

    @Override
    public List<DeliveryNoteForm> getAllDeliveryNotes() {
        List<DeliveryNote> deliveryNotes = deliveryNoteRepository.findAll();
        return getDeliveryNoteForms(deliveryNotes);
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
        Long company_id = deliveryNoteForm.getCompany().getId();
        Long customer_id = deliveryNoteForm.getCustomer().getId();
        String columnDeliveryNoteForm = new JsonConverter().convertToDatabaseColumn(deliveryNoteForm);

        deliveryNote.setCompanyId(company_id);
        deliveryNote.setCustomerId(customer_id);
        deliveryNote.setDeliveryNoteForm(columnDeliveryNoteForm);
        deliveryNoteRepository.save(deliveryNote);
    }

    @Override
    public void addNew(DeliveryNoteForm deliveryNoteForm) {

        Delivery delivery = new Delivery();
        Long companyId = deliveryNoteForm.getCompany().getId();
        String companyName = deliveryNoteForm.getCompany().getCompanyName();
        String companyAddress = deliveryNoteForm.getCompany().getAddress();

        Long customerId = deliveryNoteForm.getCustomer().getId();
        String customerName = deliveryNoteForm.getCustomer().getCustomerName();

        String driverName = deliveryNoteForm.getDriverName();

        List<UnForm> unForms = deliveryNoteForm.getUnForms();

        MaterialRow materialRow = new MaterialRow();
        MaterialRow materialRow2 = new MaterialRow();

//        materialRow.setUnNumber("12222222");
//        materialRow.setQuantityPiece(33333333L);
//
//        materialRow2.setUnNumber("4444");
//        materialRow2.setQuantityPiece(5555L);

        List<MaterialRow> materialRows = new ArrayList<>();
        materialRows.add(materialRow);
        materialRows.add(materialRow2);

        unForms
                .forEach(unForm -> {
                    System.out.println("+++++++++" + unForm.getQuantityPiece());
                    System.out.println("======" + unForm.getUnList());
                });

//        unForms.forEach(unForm -> {
//            materialRow.setQuantityPiece(unForm.getQuantityPiece());
//            materialRow.setUnNumber(unForm.getUnList().get(0).getUnNumber());
//            materialRows.add(materialRow);
//        });

        System.out.println("WWWWWWWW    " + materialRow);
        System.out.println("RRRRRRRR   " + materialRows);

        delivery.setCompanyId(companyId);
        delivery.setCompanyName(companyName);
        delivery.setCompanyAddress(companyAddress);
        delivery.setCustomerId(customerId);
        delivery.setCustomerName(customerName);
        delivery.setDriverName(driverName);

        delivery.setMaterialRows(materialRows);

        System.out.println(delivery);

        deliveryRepository.save(delivery);
    }
}
