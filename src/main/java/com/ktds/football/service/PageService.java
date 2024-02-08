package com.ktds.football.service;

import com.ktds.football.dto.PageDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PageService {

    private final int PER_PAGE = 15; // 한 페이지당 게시글 갯수
    private final int PER_SCREEN = 5; // 페이지네이션에서 보여줄 페이지 갯수

    public Map<String, Integer> findStartPage(int currentPage) {
        int startPostNum = (currentPage - 1) * PER_PAGE;
        int endPostNum = startPostNum + PER_PAGE;

        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("startPostNum", startPostNum);
        pageMap.put("perPage", PER_PAGE);

        return pageMap;
    }

    public PageDTO pagingParam(int currentPage, int totalCount) {

        int pageMax = totalCount % PER_PAGE > 0 ? (totalCount / PER_PAGE) + 1 : totalCount / PER_PAGE;

        int startPage = Math.max(1, currentPage - 2);
        int endPage = startPage + PER_SCREEN - 1;
        if(endPage > pageMax) {
            endPage = pageMax;
            startPage = Math.max(endPage - PER_SCREEN + 1, 1);
        }

        PageDTO pageDTO = new PageDTO();
        pageDTO.setCurrentPage(currentPage);
        pageDTO.setMaxPage(pageMax);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);

        return pageDTO;

    }
}
