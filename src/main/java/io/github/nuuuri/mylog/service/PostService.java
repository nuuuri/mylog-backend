package io.github.nuuuri.mylog.service;

import io.github.nuuuri.mylog.data.entity.Category;
import io.github.nuuuri.mylog.data.entity.Post;
import io.github.nuuuri.mylog.data.entity.User;
import io.github.nuuuri.mylog.data.repository.CategoryRepository;
import io.github.nuuuri.mylog.data.repository.PostRepository;
import io.github.nuuuri.mylog.data.repository.UserRepository;
import io.github.nuuuri.mylog.dto.PostDTO;
import io.github.nuuuri.mylog.dto.PostDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final BlockService blockService;
    private final CategoryService categoryService;

    @Transactional
    public void createPost(PostDetailDTO.Request postRequestDTO) {
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
        categoryService.increaseCount(category.getId());
    }

    @Transactional(readOnly = true)
    public PostDetailDTO.Response getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지않습니다."));

        return new PostDetailDTO.Response(post);
    }

    @Transactional(readOnly = true)
    public List<PostDTO> getTotalPostList() {
        return postRepository.findAllByOrderByModifiedDesc()
                .stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostDTO> getPostListByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new EntityNotFoundException("해당 카테고리가 존재하지 않습니다."));

        List<PostDTO> result = postRepository.findAllByCategoryId(category.getId())
                .stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());

        // 하위 카테고리가 있는 경우
        int subCategorySize = category.getSubCategories().size();
        if (subCategorySize > 0) {
            for (int i = 0; i < subCategorySize; i++) {
                String subCategoryName = category.getSubCategories().get(i).getName();
                List<PostDTO> subList = getPostListByCategoryName(subCategoryName);

                result.addAll(subList);
            }
        }

        return result.stream()
                .sorted(Comparator.comparing(PostDTO::getModified).reversed())
                .collect(Collectors.toList());
    }
}
