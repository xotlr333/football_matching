package com.ktds.football.repository;

import com.ktds.football.domain.Post;
import com.ktds.football.dto.MyPostPageDTO;
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

    public void delete(Long postId) {
        sql.delete("Board.delete", postId);
    }

    public List<Post> findByMemberIdPage(MyPostPageDTO myPostPageDTO) {
        return sql.selectList("Board.findByMemberIdPage", myPostPageDTO);
    }

    public int findByMemberIdCount(Long memberId) {
        return sql.selectOne("Board.findByMemberIdCount", memberId);
    }
}
