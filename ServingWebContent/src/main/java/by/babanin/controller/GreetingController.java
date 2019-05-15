package by.babanin.controller;

import by.babanin.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        return "greeting";
    }
}
