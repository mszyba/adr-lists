package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.service.CompanyService;
import eu.michalszyba.adrlist.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomer(Model model) {
        model.addAttribute("customers", customerService.getAllCustomer());
        return "customer/list-customer";
    }
}
