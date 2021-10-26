package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Customer;
import eu.michalszyba.adrlist.service.CompanyService;
import eu.michalszyba.adrlist.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final CompanyService companyService;

    public CustomerController(CustomerService customerService, CompanyService companyService) {
        this.customerService = customerService;
        this.companyService = companyService;
    }

    @ModelAttribute("companies")
    public List<Company> populateCompany() {
        return companyService.getAllCompany();
    }


    @GetMapping("/customer/list")
    public String listCustomer(Model model) {
        model.addAttribute("customers", customerService.getAllCustomer());
        return "customer/list-customer";
    }

    @GetMapping("/customer/add")
    public String getAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "/customer/add-customer";
    }

    @PostMapping("/customer/add")
    public String postAddCustomerForm(@ModelAttribute @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/customer/add-customer";
        }
        model.addAttribute("customer", customer);
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/customer/delete/{id}")
    public String deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/customer/edit/{id}")
    public String editCustomerById(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "/customer/edit-customer";
    }

    @PostMapping("/customer/edit")
    public String postEditCustomerById(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "/customer/edit-customer";
        }
        customerService.updateCustomer(customer);
        return "redirect:/customer/list";
    }
}
