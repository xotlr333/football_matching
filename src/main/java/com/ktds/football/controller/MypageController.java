package com.ktds.football.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktds.football.domain.Member;
import com.ktds.football.dto.CommonResponseDTO;
import com.ktds.football.dto.PasswordDTO;
import com.ktds.football.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("mypage")
@RequiredArgsConstructor
public class MypageController {

	private final MemberService memberService;

	@GetMapping
	public String mypage(@SessionAttribute(name = "memberInfo") Member member, Model model) {

		Member findMember = memberService.findByLoginId(member.getLoginId());

		model.addAttribute("member", findMember);

		return "mypage/mypage";
	}

	@PostMapping("update")
	@ResponseBody
	public CommonResponseDTO update(@RequestBody PasswordDTO passwordDTO
		, @SessionAttribute(name = "memberInfo") Member member) {

		CommonResponseDTO result = new CommonResponseDTO();

		// 0 : 실패, 1 : 성공
		if(!memberService.checkPassword(member.getLoginId(), passwordDTO.getPrePassword())) {
			result.setCode(0);
			result.setText("기존 비밀번호가 맞지 않습니다.");

			return result;
		}

		if(!passwordDTO.getChangePassword().equals(passwordDTO.getChangePasswordConfirm())) {
			result.setCode(0);
			result.setText("변경 비밀번호와 변경 비밀번호 확인이 일치하지 않습니다.");

			return result;
		}

		passwordDTO.setMemberId(member.getId());
		memberService.updatePassword(passwordDTO);
		result.setCode(1);
		result.setText("비밀번호 변경이 되었습니다.");

		return result;
	}

	@GetMapping("delete")
	@ResponseBody
	public CommonResponseDTO delete(HttpServletRequest request, @SessionAttribute(name = "memberInfo") Member member) {
		CommonResponseDTO result = new CommonResponseDTO();

		memberService.delete(member.getId());

		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}

		result.setCode(1);
		result.setText("탈퇴되었습니다.");

		return result;
	}
}
