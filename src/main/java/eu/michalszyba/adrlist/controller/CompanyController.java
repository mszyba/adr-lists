package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.entity.Company;
import eu.michalszyba.adrlist.repository.CompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/form/add")
    public String getCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "addCompany";
    }

    @PostMapping("/form/add")
    public String postCompanyForm(@ModelAttribute Company company, Model model) {
        model.addAttribute("company", company);
        companyRepository.save(company);
        return "redirect:/company/list";
    }

    @GetMapping("/list")
    public String company(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        return "listCompany";
    }
}
