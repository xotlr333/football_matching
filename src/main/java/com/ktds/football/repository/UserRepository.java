package com.ktds.football.repository;

import com.ktds.football.dto.User;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final SqlSessionTemplate sql;

    public int save(User user) {
        return sql.insert("User.save", user);
    }
}
