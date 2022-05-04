package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository posts;

    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();

        posts.findAll().forEach(rsl::add);
        return rsl;
    }

    public void save(Post post) {
        posts.save(post);
    }

    public Post getPost(int id) {
        return posts.findPostById(id);
    }

    public void deletePost(int id) {
        posts.delete(getPost(id));
    }
}