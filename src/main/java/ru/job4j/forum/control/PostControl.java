package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.Date;

@Controller
public class PostControl {

    private final PostService posts;

    private final UserService users;

    public PostControl(PostService posts, UserService users) {
        this.posts = posts;
        this.users = users;
    }

    @GetMapping("/post/{id}")
    public String postPage(@PathVariable int id, Model model) {
        model.addAttribute("post", posts.getPost(id));
        return "post";
    }

    @GetMapping("/new")
    public String create() {
        return "post/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute Post post) {
        post.setUser(
                users.getUser(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
        );
        post.setCreated(new Date(System.currentTimeMillis()));
        posts.save(post);
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("post", posts.getPost(id));
        return "post/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Post post) {
        Post oldPost = posts.getPost(post.getId());
        if (!post.getName().isEmpty()) {
            oldPost.setName(post.getName());
        }
        if (!post.getDescription().isEmpty()) {
            oldPost.setDescription(post.getDescription());
        }
        posts.save(oldPost);
        return "redirect:/post/" + post.getId();
    }

    @PostMapping("/post/{id}/comment/new")
    public String addComment(@PathVariable int id,
                             @ModelAttribute Comment comment) {
        Post post = posts.getPost(id);
        comment.setUser(
                users.getUser(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
        );
        post.addComment(comment);
        posts.save(post);
        return "redirect:/post/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        posts.deletePost(id);
        return "redirect:/";
    }
}
