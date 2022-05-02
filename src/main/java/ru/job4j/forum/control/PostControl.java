package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PostControl {

    private final PostService posts;

    private final UserService users;

    public PostControl(PostService posts, UserService users) {
        this.posts = posts;
        this.users = users;
    }

    @GetMapping("/post")
    public String postPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", posts.getPost(id));
        return "post";
    }

    @GetMapping("/new")
    public String create() {
        return "post/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post,
                       HttpServletRequest request) {


        post.setUser(users.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        post.setCreated(new Date(System.currentTimeMillis()));
        posts.save(post);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", posts.getPost(id));
        return "post/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Post post) {
        Post post1 = posts.getPost(post.getId());
        post1.setName(post.getName());
        post1.setDescription(post.getDescription());
        posts.save(post1);
        return "redirect:/";
    }

    @PostMapping("/post")
    public String addComment(@ModelAttribute Comment comment,
                             HttpServletRequest request) {
        int postId = Integer.parseInt(request.getParameter("post-id"));
        Post post = posts.getPost(postId);
        comment.setUser(users.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        post.addComment(comment);
        posts.save(post);
        return "redirect:/post?id=" + postId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        posts.deletePost(id);
        return "redirect:/";
    }

}
