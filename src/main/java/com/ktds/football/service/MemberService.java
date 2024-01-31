package com.ktds.football.service;

import com.ktds.football.dto.Member;
import com.ktds.football.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public int join(Member member) {
        return memberRepository.save(member);
    }

    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
