package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.ForumService;

@Controller
public class IndexControl {
    private final ForumService service;

    public IndexControl(ForumService posts) {
        this.service = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", service.getAll());
        return "index";
    }
}