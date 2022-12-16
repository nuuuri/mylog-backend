package io.github.nuuuri.mylog.controller;

import io.github.nuuuri.mylog.dto.PostDetailDTO;
import io.github.nuuuri.mylog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping()
    public void createPost(@RequestBody PostDetailDTO.Request request) {
        postService.createPost(request);
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity getPost(@PathVariable Long postId) {
        try {
            return ResponseEntity.ok()
                    .body(postService.getPost(postId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시글 정보 조회 실패");
        }
    }

    @GetMapping()
    public ResponseEntity getTotalPostList() {
        try {
            return ResponseEntity.ok()
                    .body(postService.getTotalPostList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시글 목록 조회 실패");
        }
    }

    @GetMapping(value = "/category")
    public ResponseEntity getPostListByCategory(@RequestParam String name) {
        try {
            return ResponseEntity.ok()
                    .body(postService.getPostListByCategoryName(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("카테고리 게시글 목록 조회 실패");
        }
    }
}
