package com.ktds.football.controller;

import com.ktds.football.dto.Member;
import com.ktds.football.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(@ModelAttribute Member member) {
        return null;
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
