package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.MemRepository;

import java.util.List;

@Service
public class ForumService {

    private final MemRepository repository;

    public ForumService(MemRepository repository) {
        this.repository = repository;
    }

    public void save(User user) {
        repository.saveUser(user);
    }

    public User getUser(String username) {
        return repository.findUserByUsername(username);
    }

    public void save(Post post) {
        repository.savePost(post);
    }

    public List<Post> getAll() {
        return repository.findAllPosts();
    }

    public Post getPost(int id) {
        return repository.findPostById(id);
    }

    public void deletePost(int id) {
        repository.deletePostById(id);
    }
}