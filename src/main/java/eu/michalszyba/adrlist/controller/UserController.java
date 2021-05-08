package eu.michalszyba.adrlist.controller;

import eu.michalszyba.adrlist.model.User;
import eu.michalszyba.adrlist.repository.CompanyRepository;
import eu.michalszyba.adrlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public UserController(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/list")
    public String listUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/user/list-user";
    }

    @GetMapping("/add")
    public String getAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("companies", companyRepository.findAll());
        return "/user/add-user";
    }

    @PostMapping("/add")
    public String postAddUserForm(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String editUserById(@PathVariable Long id, Model model) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
            model.addAttribute("companies", companyRepository.findAll());
            return "/user/add-user";
        } else {
            return "redirect:/user/list";
        }
    }
}
