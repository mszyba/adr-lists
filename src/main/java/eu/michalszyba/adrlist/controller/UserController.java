package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.User;
import eu.michalszyba.adrlist.service.CompanyService;
import eu.michalszyba.adrlist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    public UserController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String listUser(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "/user/list-user";
    }

    @GetMapping("/add")
    public String getAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("companies", companyService.getAllCompany());
        return "/user/add-user";
    }

    @PostMapping("/add")
    public String postAddUserForm(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/user/add-user";
        }
        model.addAttribute("user", user);
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditUserById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("companies", companyService.getAllCompany());
        return "/user/add-user";
    }

//    @PostMapping("/edit")
//    public String postEditUserById(@ModelAttribute User user, Model model) {
//        model.addAttribute("user", user);
//        userService.updateUser(user);
//        return "redirect:/user/list";
//    }
}
