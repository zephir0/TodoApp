package com.todoapp.web;

import com.todoapp.account.AccountService;
import com.todoapp.exceptions.DeletingAccountException;
import com.todoapp.exceptions.WrongOldPasswordException;
import com.todoapp.user.dto.UserCredentialsDto;
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
    String getMyAccountDetails(Model model) {
        return "my-account";
    }

    @GetMapping("/myaccount/details")
    String accountDetails(Model model) {
        String loginName = accountService.getActiveUserLogin();
        String passwordHash = accountService.getActiveUserPasswordHash();
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
            return "redirect:/myaccount/password";
        }
        try {
            accountService.changePassword(userCredentialsDto);
        } catch (WrongOldPasswordException e) {
            bindingResult.rejectValue("password", "password.wrong", "Wrong password");
            return "redirect:/myaccount/password";
        }
        model.addAttribute("successMsg", "Password successfully changed");
        return "password-change-page";
    }

    @GetMapping("myaccount/deleteaccount")
    String getDeleteAccountPage() {
        return "delete-account-page";
    }

    @PostMapping("/myaccount/deleteaccount")
    String deleteAccount() {
        try {
            accountService.deleteAccount();
        } catch (DeletingAccountException e) {
            throw new DeletingAccountException("Cannot delete account");
        }
        return "redirect:/logout";
    }
}


