package com.ktds.football.repository;

import com.ktds.football.dto.Request;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RequestRepository {

    private final SqlSessionTemplate sql;

    public void add(Request request) {
        sql.insert("Request.add", request);
    }
}
