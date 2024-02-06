package com.ktds.football.repository;

import com.ktds.football.dto.Post;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public List<Post> findPage(Map<String, Integer> pageMap) {
        return sql.selectList("Board.findPage", pageMap);
    }

    public int findAllCount() {
        return sql.selectOne("Board.findAllCount");
    }

    public void add(Post post) {
        sql.insert("Board.add", post);
    }

    public void update(Post post) {
        sql.update("Board.update", post);
    }
}
