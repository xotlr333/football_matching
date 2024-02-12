package com.ktds.football.controller;

import com.ktds.football.domain.Member;
import com.ktds.football.domain.RequestStatus;
import com.ktds.football.dto.CommonResponseDTO;
import com.ktds.football.dto.PageDTO;
import com.ktds.football.domain.Request;
import com.ktds.football.dto.RequestRequestDTO;
import com.ktds.football.dto.RequestResponseDTO;
import com.ktds.football.service.BoardService;
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
    private final BoardService boardService;

    @PostMapping("add")
    public String add(@ModelAttribute Request request){

        request.setStatus(RequestStatus.APPROVE);
        requestService.add(request);

        boardService.checkPeopleCount(request.getPostId());

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

        return "request/list";
    }

    @GetMapping("delete")
    @ResponseBody
    public CommonResponseDTO delete(
        @RequestParam(name = "requestId") Long requestId
        , @RequestParam(name = "postId") Long postId){

        requestService.delete(requestId);
        boardService.checkPeopleCount(postId);

        CommonResponseDTO result = new CommonResponseDTO();
        result.setCode(1);
        result.setText("취소되었습니다.");

        return result;

    }

    @GetMapping("list/post/{postId}")
    public String requestOfPost(
        @PathVariable(name = "postId") Long postId
        , @RequestParam(name = "page", defaultValue = "1") int currentPage
        , @SessionAttribute(name = "memberInfo", required = false) Member member
        , Model model) {

        List<RequestResponseDTO> requestList = requestService.findPageOfPost(currentPage, postId);
        PageDTO paging = requestService.pagingParamOfPost(currentPage, postId);

        model.addAttribute("requestList", requestList);
        model.addAttribute("paging", paging);
        model.addAttribute("postId", postId);

        return "request/requestOfPost";
    }

    @PostMapping("refuse")
    @ResponseBody
    public CommonResponseDTO refuse(@RequestBody RequestRequestDTO requestDTO) {
        CommonResponseDTO result = new CommonResponseDTO();

        requestService.refuse(requestDTO.getId());
        boardService.checkPeopleCount(requestDTO.getPostId());

        result.setCode(1);
        result.setText("거절하였습니다.");

        return result;
    }


}
