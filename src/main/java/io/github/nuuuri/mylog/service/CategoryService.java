package io.github.nuuuri.mylog.service;

import io.github.nuuuri.mylog.data.entity.Category;
import io.github.nuuuri.mylog.data.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void createCategory (String name, Category parent) {
        Category category = Category.builder()
                .name(name)
                .parent(parent)
                .build();

        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Category getCategory (Long id) {
        return categoryRepository.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<Category> getCategoryList() {
        return categoryRepository.findAllByParentIdIsNull();
    }
}
