package com.ktds.football.controller;

import com.ktds.football.dto.Category;
import com.ktds.football.dto.PageDTO;
import com.ktds.football.service.CategoryService;
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
    private final CategoryService categoryService;

    @GetMapping
    public String findPage(
            @RequestParam(name = "page", defaultValue = "1") int currentPage
//            , @SessionAttribute(name = "memberId", required = false) Long memberId
            , Model model) {

//        System.out.println("loginMemberId : " + memberId);

        List<Post> postList = boardService.findPage(currentPage);
        PageDTO paging = boardService.pagingParam(currentPage);

        model.addAttribute("postList", postList);
        model.addAttribute("paging", paging);

        return "list";
    }

    @GetMapping("detail/{postId}")
    public String findById(@PathVariable(name = "postId") Long postId, Model model) {

        Post findPost = boardService.findById(postId);
        String categoryTitle = categoryService.findById(findPost.getCategoryId());

        model.addAttribute("post", findPost);
        model.addAttribute("category", categoryTitle);

        return "detail";
    }


    @GetMapping("add")
    public String add(Model model){

        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);

        return "add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute Post post) {

        // TODO : 아이디 매핑
        post.setStatus("진행중");

        boardService.add(post);

        return "redirect:/board";
    }

    @GetMapping("update/{postId}")
    public String update(@PathVariable(name = "postId") Long postId, Model model) {

        Post findPost = boardService.findById(postId);
        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);
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
