package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/") //домашняя страница
    public String home(Model mav) {
        mav.addAttribute("listUser", userService.getListUsers());
        return "all-users";
    }

    @GetMapping("/createNewUser")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping() //сохранение
    public String save(@ModelAttribute("user") User theuser){
        userService.addUser(theuser);
        return "redirect:/";
    }

    @GetMapping("/editUser/{id}")//редактирование
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String update(@ModelAttribute("update") User user) {
        userService.changeUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/deleteUser/{id}") //удаление
    public String deleteById(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/";
    }
}
