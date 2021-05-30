package eu.michalszyba.adrlist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.michalszyba.adrlist.converter.JsonConverter;
import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import eu.michalszyba.adrlist.form.UnForm;
import eu.michalszyba.adrlist.model.*;
import eu.michalszyba.adrlist.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/delivery-note")
public class DeliveryNoteFormController {

    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UnService unService;
    private final DeliveryNoteService deliveryNoteService;
    private final PackagingService packagingService;

    public DeliveryNoteFormController(CompanyService companyService,
                                      CustomerService customerService,
                                      UnService unService,
                                      DeliveryNoteService deliveryNoteService, PackagingService packagingService) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.unService = unService;
        this.deliveryNoteService = deliveryNoteService;
        this.packagingService = packagingService;
    }

    @ModelAttribute("unList")
    public List<Un> populateUnForm() {
        return unService.getAllUn();
    }

    @ModelAttribute("companies")
    public List<Company> populateCompany() {
        return companyService.getAllCompany();
    }

    @ModelAttribute("customers")
    public List<Customer> populateCustomer() {
        return customerService.getAllCustomer();
    }

    @ModelAttribute("packagingList")
    public List<Packaging> populatePackaging() {
        return packagingService.getAllPackages();
    }

    @GetMapping("/add")
    public String getForm(Model model) {
        model.addAttribute("deliveryNoteForm", new DeliveryNoteForm());
        return "/form/add-delivery-note-form";
    }

    @RequestMapping(value = "/add", params = {"addRow"})
    public String addRow(DeliveryNoteForm deliveryNoteForm) {
        deliveryNoteForm.getUnForms().add(new UnForm());
        return "/form/add-delivery-note-form";
    }

    @PostMapping(value = "/add", params = {"saveForm"})
    public String postFirstStepForm(DeliveryNoteForm deliveryNoteForm, Model model) {
        DeliveryNote deliveryNote = new DeliveryNote();

        Long company_id = deliveryNoteForm.getCompany().getId();
        Long customer_id = deliveryNoteForm.getCustomer().getId();
        String columnDeliveryNoteForm = new JsonConverter().convertToDatabaseColumn(deliveryNoteForm);

        deliveryNote.setCompanyId(company_id);
        deliveryNote.setCustomerId(customer_id);
        deliveryNote.setDeliveryNoteForm(columnDeliveryNoteForm);


//        List<UnForm> unForms = deliveryNoteForm.getUnForms();
//
//        String unFormsJson = new ObjectMapper().writeValueAsString(unForms);
//        String companyJson = new ObjectMapper().writeValueAsString(deliveryNoteForm.getCompany());
//        String customerJson = new ObjectMapper().writeValueAsString(deliveryNoteForm.getCustomer());

        deliveryNoteService.add(deliveryNote);

        return "redirect:/delivery-note/list";
    }
}
