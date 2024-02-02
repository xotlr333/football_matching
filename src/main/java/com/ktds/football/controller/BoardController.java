package com.ktds.football.controller;

import lombok.Getter;
import org.springframework.ui.Model;
import com.ktds.football.dto.Post;
import com.ktds.football.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String findAll(Model model) {

        List<Post> postList = boardService.findAll();

        model.addAttribute("postList", postList);

        return "list";
    }

    @GetMapping("detail/{postId}")
    public String findById(@PathVariable Long postId, Model model) {

        Post findPost = boardService.findById(postId);

        model.addAttribute("post", findPost);


        return "detail";
    }


}
