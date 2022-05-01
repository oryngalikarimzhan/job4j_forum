package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ForumService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostControl {

    private final ForumService service;

    public PostControl(ForumService posts) {
        this.service = posts;
    }

    @GetMapping("/post")
    public String postPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", service.getPost(id));
        return "post";
    }

    @GetMapping("/new")
    public String create() {
        return "post/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        post.setUser(user);
        service.save(post);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", service.getPost(id));
        return "post/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Post post) {
        Post post1 = service.getPost(post.getId());
        post1.setName(post.getName());
        post1.setDescription(post.getDescription());
        service.save(post1);
        return "redirect:/";
    }

    @PostMapping("/post")
    public String addComment(@ModelAttribute Comment comment,
                             HttpServletRequest request) {
        int postId = Integer.parseInt(request.getParameter("post-id"));
        Post post = service.getPost(postId);
        comment.setUser((User) request.getSession().getAttribute("user"));
        post.addComment(comment);
        service.save(post);
        return "redirect:/post?id=" + postId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        service.deletePost(id);
        return "redirect:/";
    }

}
