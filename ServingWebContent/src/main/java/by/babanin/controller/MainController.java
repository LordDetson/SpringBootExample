package by.babanin.controller;

import by.babanin.dao.MessageRepository;
import by.babanin.model.Message;
import by.babanin.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private final MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/messages")
    public String messages(@RequestParam(required = false, defaultValue = "") String valueFilter, Model model) {
        Iterable<Message> messages;
        if (valueFilter.isEmpty()) {
            messages = messageRepository.findAll();
        } else {
            messages = messageRepository.findByTag(valueFilter);
        }
        model.addAttribute("messages", messages);
        model.addAttribute("valueFilter", valueFilter);
        return "messages";
    }

    @PostMapping("/addNewMessage")
    public String addNewMessage(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam MultipartFile file
    ) throws IOException {
        message.setAuthor(user);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getErrorMap(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("message", message);
        } else {
            if (!file.isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFilename = UUID.randomUUID().toString();
                String filename = uuidFilename + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + filename));
                message.setFilename(filename);
            }
            model.addAttribute("message", null);
            messageRepository.save(message);
        }
        model.addAttribute("messages", messageRepository.findAll());
        return "messages";
    }

}
