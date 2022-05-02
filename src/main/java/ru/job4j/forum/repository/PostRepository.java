package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Post;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("select distinct p from Post p join fetch p.user left join fetch p.comments where p.id = :id")
    Post findPostById(@Param("id") int id);

    @Query("select distinct p from Post p join fetch p.user left join fetch p.comments")
    Collection<Post> findAll();
}