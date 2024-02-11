package com.ktds.football.controller;

import com.ktds.football.domain.Member;
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

        if(findMember.getPassword().equals(member.getPassword())) {
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

        // TODO : 아이디 중복 체크
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


}
