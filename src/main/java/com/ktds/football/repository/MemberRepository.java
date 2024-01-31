package com.ktds.football.repository;

import com.ktds.football.dto.Member;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sql;

    public int save(Member user) {
        return sql.insert("User.save", user);
    }
}
