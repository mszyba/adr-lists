package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.entity.Company;
import eu.michalszyba.adrlist.repository.CompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        return "company/addCompany";
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
        return "company/listCompany";
    }

    @GetMapping("/edit/{id}")
    public String editCompanyById(@PathVariable Long id, Model model) {
        Optional<Company> companyById = companyRepository.findById(id);
        model.addAttribute("company", companyById);
        return "company/addCompany";
        //        TODO: need add, if optional empty return error
    }
}
