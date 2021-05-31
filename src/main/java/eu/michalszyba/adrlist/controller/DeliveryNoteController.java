package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.form.DeliveryNoteForm;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.DeliveryNote;
import eu.michalszyba.adrlist.service.CompanyService;
import eu.michalszyba.adrlist.service.DeliveryNoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/delivery-note")
public class DeliveryNoteController {

    private final DeliveryNoteService deliveryNoteService;
    private final CompanyService companyService;

    public DeliveryNoteController(DeliveryNoteService deliveryNoteService, CompanyService companyService) {
        this.deliveryNoteService = deliveryNoteService;
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String allDeliveryNote(Model model) {
        List<DeliveryNote> allDeliveryNotes = deliveryNoteService.getAllDeliveryNotes();
        model.addAttribute("allDeliveryNotes", allDeliveryNotes);
        return "/delivery-note/list-delivery-note-all";
    }

    @GetMapping("/list/company/{companyId}")
    public String listForCompanyId(@PathVariable Long companyId, Model model) {
        List<DeliveryNote> allDeliveriesForCompany = deliveryNoteService.getAllDeliveriesForCompany(companyId);
        Company companyById = companyService.getCompanyById(companyId);

        model.addAttribute("allDeliveriesForCompany", allDeliveriesForCompany);
        model.addAttribute("company", companyById);
        return "/delivery-note/list-delivery-note-company";
    }

    @GetMapping("/list/company/json/{companyId}")
    public String listForCompanyIdJson(@PathVariable Long companyId, Model model) {
        List<DeliveryNoteForm> allDeliveriesFormForCompanyId = deliveryNoteService.getAllDeliveriesFormForCompanyId(companyId);

        System.out.println(allDeliveriesFormForCompanyId);
        model.addAttribute("allDeliveriesForCompany", allDeliveriesFormForCompanyId);
        model.addAttribute("company", companyService.getCompanyById(companyId));

        return"/delivery-note/list-delivery-note-company-test";
    }
}
