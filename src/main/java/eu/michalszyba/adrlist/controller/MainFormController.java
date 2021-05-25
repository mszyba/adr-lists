package eu.michalszyba.adrlist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.michalszyba.adrlist.form.MainForm;
import eu.michalszyba.adrlist.form.UnForm;
import eu.michalszyba.adrlist.model.AdrList;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Customer;
import eu.michalszyba.adrlist.model.Un;
import eu.michalszyba.adrlist.repository.AdrListRepository;
import eu.michalszyba.adrlist.repository.MainFormRepository;
import eu.michalszyba.adrlist.service.AdrListService;
import eu.michalszyba.adrlist.service.CompanyService;
import eu.michalszyba.adrlist.service.CustomerService;
import eu.michalszyba.adrlist.service.UnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/form")
public class MainFormController {

    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UnService unService;
    private final AdrListService adrListService;

    public MainFormController(CompanyService companyService,
                              CustomerService customerService,
                              UnService unService,
                              AdrListService adrListService) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.unService = unService;
        this.adrListService = adrListService;
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

    @GetMapping("/first-step")
    public String getFirstStepForm(Model model) {
        model.addAttribute("mainForm", new MainForm());
        return "/form/first-step-form";
    }

    @RequestMapping(value = "/first-step", params = {"addRow"})
    public String addRow(MainForm mainForm) {
        mainForm.getUnForms().add(new UnForm());
        return "/form/first-step-form";
    }

    @PostMapping(value = "/first-step", params = {"saveForm"})
    public String postFirstStepForm(MainForm mainForm, Model model) throws JsonProcessingException {
        System.out.println(mainForm);

        List<UnForm> unForms = mainForm.getUnForms();

        String unFormsJson = new ObjectMapper().writeValueAsString(unForms);
        String companyJson = new ObjectMapper().writeValueAsString(mainForm.getCompany());
        String customerJson = new ObjectMapper().writeValueAsString(mainForm.getCustomer());

        AdrList adrList = new AdrList();
        adrList.setCompany(companyJson);
        adrList.setCustomer(customerJson);
        adrList.setFirstName(mainForm.getFirstName());
        adrList.setLastName(mainForm.getLastName());
        adrList.setUnRows(unFormsJson);

        adrListService.saverAdrList(adrList);

        model.addAttribute("mainForm", mainForm);
        return "redirect:/adr/list";
    }

    @GetMapping("/second-step")
    public String getSecondStepForm(Model model) {
        return "/form/second";
    }
}
