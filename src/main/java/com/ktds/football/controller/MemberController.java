package com.ktds.football.controller;

import java.util.Map;

import com.ktds.football.service.ShaUtil;
import com.ktds.football.domain.Member;
import com.ktds.football.dto.CommonResponseDTO;
import com.ktds.football.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ShaUtil shaUtil;

    @GetMapping("login")
    public String login(){
        return "member/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute Member member, HttpServletRequest request) {

        Member findMember = memberService.findByLoginId(member.getLoginId());

        HttpSession session = request.getSession();
        session.setAttribute("memberInfo", findMember);

        return "redirect:/board";
    }

    @PostMapping("login/check")
    public @ResponseBody boolean loginCheck(@RequestBody Member member) {

        Member findMember = memberService.findByLoginId(member.getLoginId());

        if(findMember == null) {
            return false;
        }

        if(findMember.getPassword().equals(shaUtil.sha256Encode(member.getPassword()))) {
            return true;
        }

        return false;
    }

    @GetMapping("join")
    public String join() {
        return "member/join";
    }

    @PostMapping("join")
    public String join(@ModelAttribute Member member) {

        member.setPassword(shaUtil.sha256Encode(member.getPassword()));

        int result = memberService.join(member);
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }


        return "redirect:/user/login";
    }

    @PostMapping("join/check")
    @ResponseBody
    public CommonResponseDTO checkDuplicationLoginId(@RequestBody Map<String, String> req) {
        System.out.println("loginId : " + req.get("loginId"));
        int findByLoginIdCount = memberService.findByLoginIdCount(req.get("loginId"));

        CommonResponseDTO result = new CommonResponseDTO();

        if(findByLoginIdCount > 0) {
            result.setCode(0);
            result.setText("이미 존재하는 아이디입니다.");
            return result;
        }

        result.setCode(1);
        return result;

    }

}
