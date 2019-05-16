package by.babanin.controller;

import by.babanin.model.Role;
import by.babanin.model.User;
import by.babanin.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String saveUser(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form,
            @RequestParam String username
    ) {
        userService.save(user, username, form);
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateUser(user, username, password, email);
        return "redirect:/user/profile";
    }

    @GetMapping("/subscribe/{userChannel}")
    public String subscribe(
            @AuthenticationPrincipal User user,
            @PathVariable User userChannel
    ) {
        userService.subscribe(userChannel, user);
        return "redirect:/user-messages/" + userChannel.getId();
    }

    @GetMapping("/unsubscribe/{userChannel}")
    public String unsubscribe(
            @AuthenticationPrincipal User user,
            @PathVariable User userChannel
    ) {
        userService.unsubscribe(userChannel, user);
        return "redirect:/user-messages/" + userChannel.getId();
    }

    @GetMapping("/{type}/{user}/list")
    public String userList(
            @PathVariable String type,
            @PathVariable User user,
            Model model
    ) {
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);
        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }
        return "subscriptions";
    }
}
