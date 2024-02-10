package com.ktds.football.controller;

import com.ktds.football.dto.Member;
import com.ktds.football.dto.PageDTO;
import com.ktds.football.dto.Post;
import com.ktds.football.dto.Request;
import com.ktds.football.dto.RequestResponseDTO;
import com.ktds.football.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public String findPage(
            @RequestParam(name = "page", defaultValue = "1") int currentPage
            , @SessionAttribute(name = "memberInfo", required = false) Member member
            , Model model) {

        List<RequestResponseDTO> requestList = requestService.findPage(currentPage, member.getId());
        PageDTO paging = requestService.pagingParam(currentPage, member.getId());

        model.addAttribute("requestList", requestList);
        model.addAttribute("paging", paging);

        return "requestList";
    }

    @GetMapping("delete/{requestId}")
    public String delete(@PathVariable(name = "requestId") Long requestId){

        requestService.delete(requestId);

        return "redirect:/request";

    }


}
