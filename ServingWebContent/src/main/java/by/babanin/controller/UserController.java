package by.babanin.controller;

import by.babanin.dao.UserRepository;
import by.babanin.model.Role;
import by.babanin.model.User;
import by.babanin.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @PostMapping
    public String saveUser(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> map,
            @RequestParam String username
    ) {
        user.setUsername(username);
        Set<String> roleSet = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        map.keySet().stream()
                .filter(roleSet::contains)
                .forEach(s -> user.getRoles().add(Role.valueOf(s)));

        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
}
