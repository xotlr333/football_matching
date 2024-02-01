package com.ktds.football.controller;

import com.ktds.football.dto.Member;
import com.ktds.football.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute Member member, HttpServletRequest request) {

        Member findMember = memberService.findByLoginId(member.getLoginId());

//        HttpSession session = request.getSession();
//        session.setAttribute("memberId", findMember.getId());

        return "login";
    }

    @PostMapping("login/check")
    public @ResponseBody Member loginCheck(@RequestBody Member member) {

        Member findMember = memberService.findByLoginId(member.getLoginId());

        return findMember;
    }

    @GetMapping("join")
    public String join() {
        return "join";
    }

    @PostMapping("join")
    public String join(@ModelAttribute Member member) {
        int result = memberService.join(member);
        return "redirect:/user/login";
    }




}
