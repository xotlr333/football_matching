package com.ktds.football.controller;

import com.ktds.football.dto.Request;
import com.ktds.football.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("request")
public class RequestController {

    private final RequestService requestService;

    @PostMapping("add")
    public String add(@ModelAttribute Request request){

        request.setStatus("요청");

        requestService.add(request);

        return "redirect:/board";
    }


}
