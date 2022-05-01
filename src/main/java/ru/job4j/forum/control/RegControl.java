package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ForumService;

@Controller
public class RegControl {

    private final ForumService service;

    public RegControl(ForumService service) {
        this.service = service;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        User user1 = service.getUser(user.getUsername());
        if (user1 != null) {
            return "redirect:/reg?error=true&username=" + user.getUsername();
        }
        service.save(user);
        return "redirect:/login?reg=true";
    }

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false) String error,
                          @RequestParam(value = "username", required = false) String username,
                          Model model) {
        String message = null;
        if (error != null) {
            message = "The user with this username = \"" + username + "\" is already exists";
        }
        model.addAttribute("message", message);
        return "reg";
    }
}