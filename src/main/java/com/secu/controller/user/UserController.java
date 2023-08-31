package com.secu.controller.user;

import com.secu.domain.Account;
import com.secu.domain.AccountDto;
import com.secu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;


@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;



    @GetMapping("/mypage")
    public String mypage(){
        return "/user/mypage";
    }


    @GetMapping("/login")
    public String loginForm
            ( @RequestParam(required = false) boolean error
            , @RequestParam(required = false) String exception
            , Model model
            ){

        model.addAttribute("account", new AccountDto());

        return "user/login/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            log.info("User: '" +((Account)authentication.getPrincipal()).getUsername() + "' LOGOUT");
        }

        return "redirect:/";
    }


    @GetMapping("denied")
    public String accessDenied
            ( @RequestParam(required = false) String exception
            , Model model
            ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        model.addAttribute("username", account.getUsername());

        return "info/accessDenied";
    }


    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute("account", new AccountDto());

        return "user/login/registerForm";
    }



    @PostMapping("/register")
    public String createUser(AccountDto accountDto){

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);
        account.setRole("USER");
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        userService.createUser(account);

        return "redirect:/";
    }

}
