package com.example.todoapp.web;

import com.example.todoapp.account.AccountService;
import com.example.todoapp.user.User;
import com.example.todoapp.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAccountController {
    private final AccountService accountService;

    public MyAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/myaccount")
    String myAccount() {
        return "myaccount";
    }

    @GetMapping("/myaccount/details")
    String accountDetails(Model model) {
        String accountDetailsMessage = accountService.accountDetailsMessage();
        model.addAttribute("accountDetailsMessage", accountDetailsMessage);
        return "myaccount";
    }

    @GetMapping("/myaccount/password")
    String changePassword(Model model) {
        String accountDetailsMessage = accountService.accountDetailsMessage();
        model.addAttribute("accountDetailsMessage", accountDetailsMessage);
        return "myaccount";
    }
}
