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

    @Transactional(readOnly = true)
    public List<Category> getCategoryList() {
        return categoryRepository.findAllByParentIdIsNull();
    }
}
