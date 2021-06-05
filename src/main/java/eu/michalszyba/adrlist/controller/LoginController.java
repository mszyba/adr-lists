package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final CompanyService companyService;

    public LoginController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("companies", companyService.getAllCompany());
        return "/company/list-company";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/login/login-form";
    }

    @GetMapping("login-error")
    public String loginFormError(Model model) {
        model.addAttribute("loginError", true);
        return "/login/login-form";
    }
}
