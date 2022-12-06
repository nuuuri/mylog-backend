package io.github.nuuuri.mylog.service;

import io.github.nuuuri.mylog.data.entity.Category;
import io.github.nuuuri.mylog.data.entity.Post;
import io.github.nuuuri.mylog.data.entity.User;
import io.github.nuuuri.mylog.data.repository.BlockRepository;
import io.github.nuuuri.mylog.data.repository.CategoryRepository;
import io.github.nuuuri.mylog.data.repository.PostRepository;
import io.github.nuuuri.mylog.data.repository.UserRepository;
import io.github.nuuuri.mylog.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BlockService blockService;

    @Transactional
    public Long createPost(PostDTO.Request postRequestDTO) {
        User user = userRepository.findByUserId(postRequestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        Category category = categoryRepository.findById(postRequestDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("해당 카테고리가 존재하지 않습니다."));

        Post post = Post.builder()
                .user(user)
                .category(category)
                .title(postRequestDTO.getTitle())
                .build();

        Long postId = postRepository.save(post).getId();
        blockService.createBlocks(postId, postRequestDTO.getBlocks());

        return postId;
    }

    @Transactional
    public PostDTO.Response getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지않습니다."));

        return new PostDTO.Response(post);
    }
}
