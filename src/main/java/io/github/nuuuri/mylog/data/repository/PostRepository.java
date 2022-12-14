package io.github.nuuuri.mylog.data.repository;

import io.github.nuuuri.mylog.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    List<Post> findAllByOrderByModifiedDesc();

    List<Post> findAllByCategoryId(Long categoryId);
    
}
