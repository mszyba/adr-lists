package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String listCompany(Model model) {
        model.addAttribute("companies", companyService.getAllCompany());
        return "/company/list-company";
    }

    @GetMapping("/add")
    public String getAddCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "/company/add-company";
    }

    @PostMapping("/add")
    public String postAddCompanyForm(@ModelAttribute Company company, Model model) {
        model.addAttribute("company", company);
        companyService.saveCompany(company);
        return "redirect:/company/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompanyById(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
        return "redirect:/company/list";
    }

    @GetMapping("/edit/{id}")
    public String editCompanyById(@PathVariable Long id, Model model) {
            model.addAttribute("company", companyService.getCompanyById(id));
            return "/company/add-company";
    }
}
