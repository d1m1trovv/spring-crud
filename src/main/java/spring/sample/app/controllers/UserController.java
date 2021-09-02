package spring.sample.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.sample.app.entities.User;
import spring.sample.app.repositories.UserRepository;
import spring.sample.app.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/create")
    public String showCreateUserForm(User user) {
        return "create-user";
    }

    @PostMapping("/create")
    public String createUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-user";
        }

        userService.createUser(user);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "edit-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "edit-user";
        }

        userService.createUser(user);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById(id);
        userService.deleteUser(user);

        return "redirect:/index";
    }
}
