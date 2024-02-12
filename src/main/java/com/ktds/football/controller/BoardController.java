package com.ktds.football.controller;

import com.ktds.football.domain.Category;
import com.ktds.football.domain.PostStatus;
import com.ktds.football.dto.PageDTO;
import com.ktds.football.service.CategoryService;
import org.springframework.ui.Model;
import com.ktds.football.domain.Post;
import com.ktds.football.service.BoardService;
import com.ktds.football.service.RequestService;

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
    private final RequestService requestService;

    @GetMapping
    public String findPage(
            @RequestParam(name = "page", defaultValue = "1") int currentPage, Model model) {

        List<Post> postList = boardService.findPage(currentPage);
        PageDTO paging = boardService.pagingParam(currentPage);

        model.addAttribute("postList", postList);
        model.addAttribute("paging", paging);

        return "board/list";
    }

    @GetMapping("detail/{postId}")
    public String findById(@PathVariable(name = "postId") Long postId, Model model) {

        Post findPost = boardService.findById(postId);
        String categoryTitle = categoryService.findById(findPost.getCategoryId());
        int requestCount = requestService.findApproveByPostIdCount(postId);

        model.addAttribute("post", findPost);
        model.addAttribute("category", categoryTitle);
        model.addAttribute("requestCount", requestCount);

        return "board/detail";
    }


    @GetMapping("add")
    public String add(Model model){

        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);

        return "board/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute Post post) {

        post.setStatus(PostStatus.PROCEEDING);
        boardService.add(post);

        return "redirect:/board";
    }

    @GetMapping("update/{postId}")
    public String update(@PathVariable(name = "postId") Long postId, Model model) {

        Post findPost = boardService.findById(postId);
        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("post", findPost);

        return "board/update";
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
