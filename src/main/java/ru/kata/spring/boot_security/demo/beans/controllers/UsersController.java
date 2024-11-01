package ru.kata.spring.boot_security.demo.beans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.beans.models.Role;
import ru.kata.spring.boot_security.demo.beans.models.User;
import ru.kata.spring.boot_security.demo.beans.service.RoleService;
import ru.kata.spring.boot_security.demo.beans.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/show";
    }

    @GetMapping("/admin/addUser")
    public String showUserAddPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "/admin/addUser";
    }

    @GetMapping("/user")
    public String showUserForm(Model model, Principal principal) {
        User foundUser = userService.findByEmail(principal.getName());
        model.addAttribute("user", foundUser);
        return "/users/userPage";
    }


    @PostMapping("/admin/addNewUser")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/editUserPage")
    public String showUserEditPage(@RequestParam("id") int id, Model model) {
        User foundUser = userService.findById(id);
        model.addAttribute("user", foundUser);
        model.addAttribute("roles", roleService.findAll());
        return "/admin/editUserPage";
    }

    @PostMapping("/admin/editUser")
    public String editUser(@ModelAttribute User user, @RequestParam Set<Role> roles) {
        userService.updateUser(user.getId(), user, roles);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteUser")
    public String deleteUser(@ModelAttribute User user) {
        userService.deleteUser(user.getId());
        return "redirect:/admin";
    }

}
