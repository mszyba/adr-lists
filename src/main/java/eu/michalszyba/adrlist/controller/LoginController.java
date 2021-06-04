package eu.michalszyba.adrlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

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
