package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.*;
import eu.michalszyba.adrlist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DeliveryController {

    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UnService unService;
    private final DeliveryNoteService deliveryNoteService;
    private final PackagingService packagingService;
    private final DeliveryService deliveryService;

    public DeliveryController(CompanyService companyService, CustomerService customerService, UnService unService, DeliveryNoteService deliveryNoteService, PackagingService packagingService, DeliveryService deliveryService) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.unService = unService;
        this.deliveryNoteService = deliveryNoteService;
        this.packagingService = packagingService;
        this.deliveryService = deliveryService;
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

    @ModelAttribute("packagings")
    public List<Packaging> populatePackaging() {
        return packagingService.getAllPackages();
    }

    @GetMapping("/delivery/add")
    public String getNewForm(Model model) {
        model.addAttribute("delivery", new Delivery());
        return "/form/add-new";
    }

    @RequestMapping(value = "/delivery/add", params = {"addRow"})
    public String addNewRow(Delivery delivery) {
        deliveryService.addMaterialRow(delivery);
//        delivery.getMaterialRows().add(new MaterialRow());
        return "/form/add-new";
    }

    @PostMapping(value = "/delivery/add", params = {"saveForm"})
    public String postDelivery(@ModelAttribute Delivery delivery, Model model) {

        int i = 1;
        for (MaterialRow materialRow : delivery.getMaterialRows()) {
            System.out.println("BBBBB   " +  materialRow);
        }
//        model.addAttribute("delivery", deliveryService.save(delivery));
//        return "/form/add-new";

        Delivery saveDelivery = deliveryService.save(delivery);
        System.out.println("SAVE DELIVERY " + saveDelivery);

        return "redirect:/delivery-note/list";
    }
}
