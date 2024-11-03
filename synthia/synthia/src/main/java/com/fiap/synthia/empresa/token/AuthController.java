package com.fiap.synthia.empresa.token;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/loginn")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            Credentials credentials = new Credentials(username, password);
            Token token = authService.login(credentials);
            return "redirect:/home";
        } catch (RuntimeException e) {
            model.addAttribute("loginError", true);
            return "login";
        }
    }
 
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}



