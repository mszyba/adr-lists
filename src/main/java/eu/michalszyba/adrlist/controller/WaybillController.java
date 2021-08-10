package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.*;
import eu.michalszyba.adrlist.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WaybillController {


    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UnService unService;
    private final PackagingService packagingService;
    private final DeliveryService deliveryService;

    public WaybillController(CompanyService companyService,
                             CustomerService customerService,
                             UnService unService,
                             PackagingService packagingService,
                             DeliveryService deliveryService) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.unService = unService;
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


    @GetMapping("/waybill/list")
    public String listWaybill(Model model) {
        model.addAttribute("waybills", deliveryService.getAllDeliveries());
        return "/waybill/waybill-list-all";
    }
}
