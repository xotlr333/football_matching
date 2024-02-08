package com.ktds.football.repository;

import com.ktds.football.dto.Request;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RequestRepository {

    private final SqlSessionTemplate sql;

    public void add(Request request) {
        sql.insert("Request.add", request);
    }

    public List<Request> findPage(Map<String, Integer> pageMap) {
        return sql.selectList("Request.findPage", pageMap);
    }

    public int findByMemberIdCount(Long memberId) {
        return sql.selectOne("Request.findByMemberIdCount", memberId);
    }
}
