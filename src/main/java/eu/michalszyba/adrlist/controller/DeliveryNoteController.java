package eu.michalszyba.adrlist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.List;
import java.util.Optional;

@Controller
public class DeliveryNoteController {

    private final DeliveryNoteService deliveryNoteService;
    private final CompanyService companyService;

    public DeliveryNoteController(DeliveryNoteService deliveryNoteService, CompanyService companyService) {
        this.deliveryNoteService = deliveryNoteService;
        this.companyService = companyService;
    }

    @GetMapping("/delivery-note/list")
    public String allDeliveryNote(Model model) {
        List<DeliveryNoteForm> allDeliveryNotes = deliveryNoteService.getAllDeliveryNotes();
        model.addAttribute("allDeliveryNotes", allDeliveryNotes);
        return "/delivery-note/list-delivery-note-all";
    }

    @GetMapping("/delivery-note/list/company/{companyId}")
    public String listForCompanyId(@PathVariable Long companyId, Model model) {
        List<DeliveryNote> allDeliveriesForCompany = deliveryNoteService.getAllDeliveriesForCompany(companyId);
        Company companyById = companyService.getCompanyById(companyId);

        model.addAttribute("allDeliveriesForCompany", allDeliveriesForCompany);
        model.addAttribute("company", companyById);
        return "/delivery-note/list-delivery-note-company";
    }
}
