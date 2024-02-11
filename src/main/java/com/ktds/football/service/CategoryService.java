package com.ktds.football.service;

import com.ktds.football.domain.Category;
import com.ktds.football.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public String findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
