package com.ktds.football.service;

import com.ktds.football.domain.PostStatus;
import com.ktds.football.dto.MyPostPageDTO;
import com.ktds.football.dto.PageDTO;
import com.ktds.football.domain.Post;
import com.ktds.football.dto.PostResponseDTO;
import com.ktds.football.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PageService pageService;
    private final RequestService requestService;

    public List<Post> findAll() {
        return boardRepository.findAll();
    }

    public Post findById(Long postId) {
        return boardRepository.findById(postId);
    }

    public List<PostResponseDTO> findPage(int currentPage, int categoryId) {

        Map<String, Integer> map = pageService.findStartPage(currentPage);
        map.put("categoryId", categoryId);

//        return boardRepository.findPage(pageService.findStartPage(currentPage));
        return boardRepository.findPage(map);

    }

    public PageDTO pagingParam(int currentPage, int categoryId) {

//        int totalPostCount = findAllCount();
        int totalPostCount = boardRepository.findByCategoryIdCount(categoryId);

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

    public List<Post> findByMemberIdPage(int currentPage, Long memberId) {

        Map<String, Integer> map = pageService.findStartPage(currentPage);

        MyPostPageDTO myPostPageDTO = new MyPostPageDTO();
        myPostPageDTO.setMemberId(memberId);
        myPostPageDTO.setPerPage(map.get("perPage"));
        myPostPageDTO.setStartPostNum(map.get("startPostNum"));



        return boardRepository.findByMemberIdPage(myPostPageDTO);
    }

    public PageDTO myPostPagingParam(int currentPage, Long memberId) {
        int totalPostCount = boardRepository.findByMemberIdCount(memberId);

        return pageService.pagingParam(currentPage, totalPostCount);
    }

    public String changeStatus(Long postId) {
        Post post = boardRepository.findById(postId);

        // status == PROCEEDING 일 경우
        if(post.getStatus() == PostStatus.PROCEEDING) {
            post.setStatus(PostStatus.DEADLINE);
            boardRepository.update(post);
            return "마감";
        }

        // status == DEADLINE 일 경우
        post.setStatus(PostStatus.PROCEEDING);
        boardRepository.update(post);
        return "진행중";

    }
}
