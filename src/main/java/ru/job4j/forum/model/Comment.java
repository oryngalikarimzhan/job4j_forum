package ru.job4j.forum.model;

public class Comment {
    private int id;
    private String text;
    private User user;

    public static Comment of(String text) {
        Comment comment = new Comment();
        comment.text = text;
        return comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
