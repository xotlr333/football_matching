package com.ktds.football.repository;

import com.ktds.football.dto.Category;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final SqlSessionTemplate sql;


    public List<Category> findAll() {
        return sql.selectList("Category.findAll");
    }

    public String findById(Long categoryId) {
        return sql.selectOne("Category.findById", categoryId);
    }
}
