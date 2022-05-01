package ru.job4j.forum.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ForumService;

@Controller
public class LoginControl {
    private final ForumService service;

    public LoginControl(ForumService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            @RequestParam(value = "reg", required = false) String reg,
                            Model model) {
        String message = null;
        if (error != null) {
            message = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            message = "You have been successfully logged out !!";
        }
        if (reg != null) {
            message = "You have been successfully signed in !! Now you can log in ";
        }
        model.addAttribute("message", message);
        return "login";
    }

    @PostMapping("/login")
    public String authorize(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = service.getUser(username);
        if (user == null || !password.equals(user.getPassword())) {
            return "redirect:/login?error=true";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login?logout=true";
    }
}