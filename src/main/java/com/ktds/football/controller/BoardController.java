package com.ktds.football.controller;

import com.ktds.football.dto.PageDTO;
import lombok.Getter;
import org.springframework.ui.Model;
import com.ktds.football.dto.Post;
import com.ktds.football.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String findPage(@RequestParam(name = "page", defaultValue = "1") int currentPage, Model model) {

//        List<Post> postList = boardService.findAll();

        List<Post> postList = boardService.findPage(currentPage);
        PageDTO paging = boardService.pagingParam(currentPage);

        System.out.println("startPage : " + paging.getStartPage());
        System.out.println("endPage : " + paging.getEndPage());
        System.out.println("maxPage : " + paging.getMaxPage());
        System.out.println("currentPage : " + paging.getCurrentPage());

        model.addAttribute("postList", postList);
        model.addAttribute("paging", paging);

        return "list";
    }

    @GetMapping("detail/{postId}")
    public String findById(@PathVariable Long postId, Model model) {

        Post findPost = boardService.findById(postId);

        model.addAttribute("post", findPost);

        return "detail";
    }


}
