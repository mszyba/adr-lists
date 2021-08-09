package eu.michalszyba.adrlist.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.DocumentException;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.service.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UserService userService;
    private final PdfService pdfService;
    private final PdfGenaratorUtil pdfGenaratorUtil;


    public CompanyController(CompanyService companyService, CustomerService customerService, UserService userService, PdfService pdfService, PdfGenaratorUtil pdfGenaratorUtil) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.userService = userService;
        this.pdfService = pdfService;
        this.pdfGenaratorUtil = pdfGenaratorUtil;

    }

    @GetMapping("/list")
    public String listCompany(Model model) {
        model.addAttribute("companies", companyService.getAllCompany());
        return "/company/list-company";
    }

    @GetMapping("/download-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/download-pdf-2")
    public void downloadPdf2() throws Exception {
        Map<String,String> data = new HashMap<String,String>();
        data.put("name","James");
        pdfGenaratorUtil.createPdf("testpdf",data);
    }

    @GetMapping("/add")
    public String getAddCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "/company/add-company";
    }

    @PostMapping("/add")
    public String postAddCompanyForm(@Valid Company company, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/company/add-company";
        }
        model.addAttribute("company", company);
        companyService.saveCompany(company);
        return "redirect:/company/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompanyById(@PathVariable Long id) {
        companyService.softDeleteCompanyById(id);
        return "redirect:/company/list";
    }

    @GetMapping("/edit/{id}")
    public String editCompanyById(@PathVariable Long id, Model model) {
            model.addAttribute("company", companyService.getCompanyById(id));
            return "/company/add-company";
    }

    @GetMapping("/customers/list/{id}")
    public String showCustomersByCompanyId(@PathVariable Long id, Model model) {
        model.addAttribute("customers", customerService.getAllCustomerByCompanyId(id));
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/company/list-company-customers";
    }

    @GetMapping("/users/list/{id}")
    public String showUsersByCompanyId(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.getAllUserByCompanyId(id));
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/company/list-company-users";
    }
}
