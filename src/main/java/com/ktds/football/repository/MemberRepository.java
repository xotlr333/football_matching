package com.ktds.football.repository;

import com.ktds.football.domain.Member;
import com.ktds.football.dto.PasswordDTO;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sql;

    public int save(Member member) {
        return sql.insert("Member.save", member);
    }

    public Member findByLoginId(String loginId) {
        return sql.selectOne("Member.findByLoginId", loginId);
    }

	public void updatePassword(PasswordDTO passwordDTO) {
		sql.update("Member.updatePassword", passwordDTO);
	}
}
