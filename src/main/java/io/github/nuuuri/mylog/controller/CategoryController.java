package io.github.nuuuri.mylog.controller;


import io.github.nuuuri.mylog.dto.CategoryDTO;
import io.github.nuuuri.mylog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity getCategoryList() {
        try {
            List<CategoryDTO.Response> categoryList = categoryService.getCategoryList()
                    .stream()
                    .map(CategoryDTO.Response::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok()
                    .body(categoryList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("카테고리 정보 조회 실패");
        }
    }

}
