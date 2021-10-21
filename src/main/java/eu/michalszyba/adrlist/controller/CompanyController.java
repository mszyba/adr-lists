package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UserService userService;

    public CompanyController(CompanyService companyService, CustomerService customerService, UserService userService) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "company-test";
    }

    @GetMapping("/list")
    public String listCompany(Model model) {
        model.addAttribute("companies", companyService.getAllCompany());
        return "company/list-company";
    }

    @GetMapping("/add")
    public String getAddCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "company/add-company";
    }

    @PostMapping("/add")
    public String postAddCompanyForm(@Valid Company company, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "company/add-company";
        }
        model.addAttribute("company", company);
        companyService.saveCompany(company);
        return "redirect:company/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompanyById(@PathVariable Long id) {
        companyService.softDeleteCompanyById(id);
        return "redirect:company/list";
    }

    @GetMapping("/edit/{id}")
    public String editCompanyById(@PathVariable Long id, Model model) {
            model.addAttribute("company", companyService.getCompanyById(id));
            return "company/add-company";
    }

    @GetMapping("/customers/list/{id}")
    public String showCustomersByCompanyId(@PathVariable Long id, Model model) {
        model.addAttribute("customers", customerService.getAllCustomerByCompanyId(id));
        model.addAttribute("company", companyService.getCompanyById(id));
        return "company/list-company-customers";
    }

    @GetMapping("/users/list/{id}")
    public String showUsersByCompanyId(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.getAllUserByCompanyId(id));
        model.addAttribute("company", companyService.getCompanyById(id));
        return "company/list-company-users";
    }
}
