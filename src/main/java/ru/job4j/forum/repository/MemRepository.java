package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemRepository {
    private final CopyOnWriteArrayList<Post> posts;
    private final CopyOnWriteArraySet<User> users;
    private AtomicInteger userIdCounter = new AtomicInteger(1);
    private AtomicInteger postIdCounter = new AtomicInteger(1);

    public MemRepository() {
        this.users = new CopyOnWriteArraySet<>();
        User user = User.of("admin", "123456");
        user.setId(userIdCounter.getAndIncrement());
        users.add(user);
        this.posts = new CopyOnWriteArrayList<>();
        Post post = Post.of("aaa", "aaaaaaaa");
        post.setId(postIdCounter.getAndIncrement());
        post.setCreated(new Date(System.currentTimeMillis()));
        post.setUser(user);
        Comment comment = Comment.of("hhhhh");
        comment.setUser(user);
        comment.setId(1);
        post.addComment(comment);
        posts.add(post);
    }

    public void saveUser(User user) {
        if (user.getId() == 0) {
            user.setId(userIdCounter.getAndIncrement());
            users.add(user);
        }
    }

    public User findUserByUsername(String username) {
        User user = null;
        for (User usr : users) {
            if (usr.getUsername().equals(username)) {
                user = usr;
                break;
            }
        }
        return user;
    }

    public void savePost(Post post) {
        if (post.getId() == 0) {
            post.setId(postIdCounter.getAndIncrement());
            post.setCreated(new Date(System.currentTimeMillis()));
            posts.add(post);
        } else {
            deletePostById(post.getId());
            posts.add(post);
        }
    }

    public List<Post> findAllPosts() {
        return posts;
    }

    public Post findPostById(int id) {
        Post post = null;
        for (Post pst : posts) {
            if (pst.getId() == id) {
                post = pst;
            }
        }
        return post;
    }

    public void deletePostById(int id) {
        Post post = findPostById(id);
        if (post != null) {
            posts.remove(post);
        }
    }
}
