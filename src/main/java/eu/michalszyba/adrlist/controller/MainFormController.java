package eu.michalszyba.adrlist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.michalszyba.adrlist.form.MainForm;
import eu.michalszyba.adrlist.form.UnForm;
import eu.michalszyba.adrlist.model.AdrListEntity;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Customer;
import eu.michalszyba.adrlist.model.Un;
import eu.michalszyba.adrlist.repository.AdrListRepository;
import eu.michalszyba.adrlist.repository.MainFormRepository;
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

    private final MainFormRepository mainFormRepository;
    private final AdrListRepository adrListRepository;

    public MainFormController(CompanyService companyService, CustomerService customerService, UnService unService, MainFormRepository mainFormRepository, AdrListRepository adrListRepository) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.unService = unService;
        this.mainFormRepository = mainFormRepository;
        this.adrListRepository = adrListRepository;
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
    @ResponseBody
    public String postFirstStepForm(MainForm mainForm, Model model) throws JsonProcessingException {
        System.out.println(mainForm);

        List<UnForm> unForms = mainForm.getUnForms();
        Customer mainFormCustomer = mainForm.getCustomer();

        String unFormsJson = new ObjectMapper().writeValueAsString(unForms);
        String companyJson = new ObjectMapper().writeValueAsString(mainForm.getCompany());
        String customerJson = new ObjectMapper().writeValueAsString(mainForm.getCustomer());


        System.out.println("=====================================");
        System.out.println(unFormsJson);
        System.out.println("=====================================");
        System.out.println(companyJson);
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(customerJson);

        AdrListEntity adrListEntity = new AdrListEntity();
        adrListEntity.setCompany(companyJson);
        adrListEntity.setCustomer(customerJson);
        adrListEntity.setFirstName(mainForm.getFirstName());
        adrListEntity.setLastName(mainForm.getLastName());
        adrListEntity.setUnRows(unFormsJson);

        adrListRepository.save(adrListEntity);

//        mainFormRepository.add(mainForm);
//        System.out.println(mainFormRepository.findAll());
        model.addAttribute("mainForm", mainForm);
//        return "redirect:/form/show-main-form";
        return "mainForm";
    }

    @GetMapping("/second-step")
    public String getSecondStepForm(Model model) {
        return "/form/second";
    }
}
