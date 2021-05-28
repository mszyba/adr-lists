package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.converter.JsonConverter;
import eu.michalszyba.adrlist.model.DeliveryNote;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Customer;
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
@RequestMapping("/adr")
public class AdrListFormController {

    private final DeliveryNoteService deliveryNoteService;

    public AdrListFormController(DeliveryNoteService deliveryNoteService) {
        this.deliveryNoteService = deliveryNoteService;
    }

    @GetMapping("/list")
    public String listAdr(Model model) {
        List<DeliveryNote> allDeliveryNotes = deliveryNoteService.getAllDeliveryNotes();
        model.addAttribute("allDeliveryNotes", allDeliveryNotes);

//        List<Company> allCompany = deliveryNoteService.getAllAdrList();
//        System.out.println(allCompany);
//        model.addAttribute("companies", allCompany);
//        return allCompany.toString();
        return "/adr-list/list-adr-all";
    }

    @GetMapping("/one/{id}")
    @ResponseBody
    public String oneAdr(@PathVariable Long id, Model model) {
        Optional<DeliveryNote> oneRow = deliveryNoteService.getOneRow(id);
        if (oneRow.isPresent()) {
//            String customerString = oneRow.get().getCustomer();
//            Customer customer = (Customer) new JsonConverter().convertToEntityAttribute(customerString);
//            return customer.toString();
        }
        model.addAttribute("row", deliveryNoteService.getOneRow(id));
        return deliveryNoteService.getOneRow(id).toString();
    }
}
