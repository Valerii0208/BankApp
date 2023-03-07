package com.example.bankdemo.web;

import com.example.bankdemo.entity.User;
import com.example.bankdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public String createUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.create(user);
        return "registration-success";
    }

  @GetMapping("/personal-page")
    public String personalPage(Model model) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String username = auth.getName();
      User user = userService.findByLogin(username);
      model.addAttribute("user", user);
        return "personal-page";
    }
}
