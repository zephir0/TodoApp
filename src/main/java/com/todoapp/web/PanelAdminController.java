package com.todoapp.web;


import com.todoapp.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PanelAdminController {
    private final UserService userService;

    public PanelAdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/paneladmin")
    public String panelAdmin() {
        return "admin-panel";
    }

    @GetMapping("/paneladmin/showallusers")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "show-all-users-admin-page";
    }
    @PostMapping("/paneladmin/deleteuser/{id}")
    public String showAllUsers(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/paneladmin/showallusers";
    }
}
