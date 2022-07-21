package com.example.todoapp.web;

import com.example.todoapp.account.AccountService;
import com.example.todoapp.exceptions.WrongOldPasswordException;
import com.example.todoapp.user.User;
import com.example.todoapp.user.UserService;
import com.example.todoapp.user.dto.UserCredentialsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        String loginName = accountService.getLoginName();
        String passwordHash = accountService.getPasswordHash();
        model.addAttribute("loginName", loginName);
        model.addAttribute("passwordHash", passwordHash);
        return "account-details-page";
    }

    @GetMapping("/myaccount/password")
    String getMyAccountPasswordPage(Model model,
                                    UserCredentialsDto userCredentialsDto) {
        model.addAttribute("userCredentialsDto", userCredentialsDto);
        return "password-change-page";
    }

    @PostMapping("/myaccount/password")
    String changePassword(Model model,
                          UserCredentialsDto userCredentialsDto,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userCredentialsDto", userCredentialsDto);
            return "registration";
        }
        try {
            accountService.changePassword(userCredentialsDto);
        } catch (WrongOldPasswordException e) {
            bindingResult.rejectValue("password", "password.wrong", "Wrong password");
            model.addAttribute("userCredentialsDto", userCredentialsDto);
            return "password-change-page";
        }
        model.addAttribute("userCredentialsDto", userCredentialsDto);
        model.addAttribute("successMsg", "Password successfully changed");
        return "password-change-page";
    }
}
