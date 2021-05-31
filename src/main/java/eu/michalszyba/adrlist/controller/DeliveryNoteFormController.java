package eu.michalszyba.adrlist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.michalszyba.adrlist.converter.JsonConverter;
import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import eu.michalszyba.adrlist.form.UnForm;
import eu.michalszyba.adrlist.model.*;
import eu.michalszyba.adrlist.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String postDeliveryNoteForm(@Valid DeliveryNoteForm deliveryNoteForm, BindingResult result) {
        if (result.hasErrors()) {
            return "/form/add-delivery-note-form";
        }
        deliveryNoteService.add(deliveryNoteForm);
        return "redirect:/delivery-note/list";
    }
}
