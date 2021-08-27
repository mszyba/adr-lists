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
    private final WaybillService waybillService;

    public WaybillController(CompanyService companyService,
                             CustomerService customerService,
                             UnService unService,
                             PackagingService packagingService,
                             WaybillService waybillService) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.unService = unService;
        this.packagingService = packagingService;
        this.waybillService = waybillService;
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
        model.addAttribute("waybills", waybillService.getAllWaybill());
        return "/waybill/waybill-list-all";
    }

    @GetMapping("/waybill/add")
    public String getNewForm(Model model) {
        model.addAttribute("waybill", new Waybill());
        return "/waybill/waybill-add";
    }

    @RequestMapping(value = "/waybill/add", params = {"addRow"})
    public String addNewRow(Waybill waybill) {
        waybillService.addMaterialRow(waybill);
        return "/waybill/waybill-add";
    }

    @PostMapping(value = "/waybill/add", params = {"saveForm"})
    public String postDelivery(@ModelAttribute Waybill waybill) {
        waybillService.save(waybill);
        return "redirect:/waybill/list";
    }
}
