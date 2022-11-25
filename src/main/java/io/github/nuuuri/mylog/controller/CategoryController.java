package io.github.nuuuri.mylog.controller;


import io.github.nuuuri.mylog.data.entity.Category;
import io.github.nuuuri.mylog.dto.CategoryDTO;
import io.github.nuuuri.mylog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public void createCategory(@RequestBody CategoryDTO.Request categoryRequestDTO) {
        Category parent = categoryService.getCategory(categoryRequestDTO.getParentId());

        categoryService.createCategory(categoryRequestDTO.getName(), parent);
    }

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
