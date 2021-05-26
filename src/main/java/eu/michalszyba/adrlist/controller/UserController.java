package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.User;
import eu.michalszyba.adrlist.service.CompanyService;
import eu.michalszyba.adrlist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    public UserController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @ModelAttribute("users")
    public List<User> populateUser() {
        return userService.getAllUser();
    }

    @ModelAttribute("companies")
    public List<Company> populateCompany() {
        return companyService.getAllCompany();
    }

    @GetMapping("/list")
    public String listUser() {
        return "/user/list-user";
    }

    @GetMapping("/add")
    public String getAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/add-user";
    }

    @PostMapping("/add")
    public String postAddUserForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/add-user";
        }
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.softDeleteUserById(id);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditUserById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/user/add-user";
    }

    @PostMapping("/edit")
    public String postEditUserById(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/add-user";
        }
        userService.updateUser(user);
        return "redirect:/user/list";
    }
}
