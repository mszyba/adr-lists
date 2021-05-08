package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/list")
    public String listCompany(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
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
        companyRepository.save(company);
        return "redirect:/company/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompanyById(@PathVariable Long id) {
        companyRepository.deleteById(id);
        return "redirect:/company/list";
    }

    @GetMapping("/edit/{id}")
    public String editCompanyById(@PathVariable Long id, Model model) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company companyById = optionalCompany.get();
            model.addAttribute("company", companyById);
            return "/company/add-company";
        } else {
            return "redirect:/company/list";
        }
    }
}
