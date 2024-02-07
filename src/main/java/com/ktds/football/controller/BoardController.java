package com.ktds.football.controller;

import com.ktds.football.dto.PageDTO;
import lombok.Getter;
import org.springframework.ui.Model;
import com.ktds.football.dto.Post;
import com.ktds.football.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("postList", postList);
        model.addAttribute("paging", paging);

        return "list";
    }

    @GetMapping("detail/{postId}")
    public String findById(@PathVariable(name = "postId") Long postId, Model model) {

        Post findPost = boardService.findById(postId);

        model.addAttribute("post", findPost);

        return "detail";
    }


    @GetMapping("add")
    public String add(){
        return "add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute Post post) {

        // TODO : 아이디 매핑
        post.setMemberId(1L);
        post.setCategoryId(1L);
        post.setStatus("진행중");

        boardService.add(post);

        return "redirect:/board";
    }

    @GetMapping("update/{postId}")
    public String update(@PathVariable(name = "postId") Long postId, Model model) {

        Post findPost = boardService.findById(postId);

        model.addAttribute("post", findPost);

        return "update";
    }

    @PostMapping("update/{postId}")
    public String update(
            @PathVariable(name = "postId") Long postId,
            @ModelAttribute Post post) {

        boardService.update(post);


        return "redirect:/board/detail/" + postId;
    }

    @GetMapping("delete/{postId}")
    public String delete(@PathVariable(name = "postId") Long postId) {

        boardService.delete(postId);

        return "redirect:/board";
    }

}
