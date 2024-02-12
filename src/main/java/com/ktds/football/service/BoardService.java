package com.ktds.football.service;

import com.ktds.football.domain.PostStatus;
import com.ktds.football.dto.PageDTO;
import com.ktds.football.domain.Post;
import com.ktds.football.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PageService pageService;
    private final RequestService requestService;

    // private final int PER_PAGE = 15; // 한 페이지당 게시글 갯수
    // private final int PER_SCREEN = 5; // 페이지네이션에서 보여줄 페이지 갯수

    public List<Post> findAll() {
        return boardRepository.findAll();
    }

    public Post findById(Long postId) {
        return boardRepository.findById(postId);
    }

    public List<Post> findPage(int currentPage) {

        // int startPostNum = (currentPage - 1) * PER_PAGE;
        // int endPostNum = startPostNum + PER_PAGE;
        //
        // Map<String, Integer> pageMap = new HashMap<>();
        // pageMap.put("startPostNum", startPostNum);
        // pageMap.put("perPage", PER_PAGE);
        //
        // return boardRepository.findPage(pageMap);

        return boardRepository.findPage(pageService.findStartPage(currentPage));

    }

    public PageDTO pagingParam(int currentPage) {

        // int totalPostCount = findAllCount();
        // int pageMax = totalPostCount % PER_PAGE > 0 ? (totalPostCount / PER_PAGE) + 1 : totalPostCount / PER_PAGE;
        //
        // int startPage = Math.max(1, currentPage - 2);
        // int endPage = startPage + PER_SCREEN - 1;
        // if(endPage > pageMax) {
        //     endPage = Math.max(pageMax, 1);
        //     startPage = Math.max(endPage - PER_SCREEN + 1, 1);
        // }
        //
        // PageDTO pageDTO = new PageDTO();
        // pageDTO.setCurrentPage(currentPage);
        // pageDTO.setMaxPage(pageMax);
        // pageDTO.setStartPage(startPage);
        // pageDTO.setEndPage(endPage);
        //
        // return pageDTO;

        int totalPostCount = findAllCount();

        return pageService.pagingParam(currentPage, totalPostCount);
    }

    public int findAllCount() {
        return boardRepository.findAllCount();
    }

    public void add(Post post) {
        boardRepository.add(post);
    }

    public void update(Post post) {
        boardRepository.update(post);
    }

    public void delete(Long postId) {
        boardRepository.delete(postId);
    }

	public void checkPeopleCount(Long postId) {
        Post post = findById(postId);
        int requestCount = requestService.findApproveByPostIdCount(postId);

        if(post.getStatus() == PostStatus.PROCEEDING && post.getPeople() <= requestCount) {
            post.setStatus(PostStatus.DEADLINE);
            update(post);
        }

        if(post.getStatus() == PostStatus.DEADLINE && post.getPeople() > requestCount) {
            post.setStatus(PostStatus.PROCEEDING);
            update(post);
        }
	}
}
