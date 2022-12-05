package io.github.nuuuri.mylog.controller;

import io.github.nuuuri.mylog.dto.PostDTO;
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
    public Long createPost(@RequestBody PostDTO.Request postRequestDTO) {
        return postService.createPost(postRequestDTO);
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
}