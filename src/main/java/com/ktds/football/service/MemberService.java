package com.ktds.football.service;

import com.ktds.football.domain.Member;
import com.ktds.football.dto.PasswordDTO;
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

	public boolean checkPassword(String loginId, String prePassword) {

		Member member = memberRepository.findByLoginId(loginId);

		return member.getPassword().equals(prePassword);
	}

	public void updatePassword(PasswordDTO passwordDTO) {
		memberRepository.updatePassword(passwordDTO);
	}

	public void delete(Long id) {
		memberRepository.delete(id);

	}

    public Member findById(Long memberId) {
		return memberRepository.findById(memberId);
    }
}
