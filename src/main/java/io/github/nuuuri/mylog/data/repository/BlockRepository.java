package io.github.nuuuri.mylog.data.repository;

import io.github.nuuuri.mylog.data.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {
    List<Block> findAllByPostId(Long postId);
}
