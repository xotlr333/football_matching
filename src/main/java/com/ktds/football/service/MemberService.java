package com.ktds.football.service;

import com.ktds.football.dto.Member;
import com.ktds.football.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository userRepository;

    public int join(Member user) {
        return userRepository.save(user);
    }
}
