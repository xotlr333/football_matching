package com.ktds.football.repository;

import com.ktds.football.dto.Post;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;

    public List<Post> findAll() {
        return sql.selectList("Board.findAll");
    }

    public Post findById(Long postId) {
        return sql.selectOne("Board.findById", postId);
    }
}
