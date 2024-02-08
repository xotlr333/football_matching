package com.ktds.football.service;


import com.ktds.football.dto.PageDTO;
import com.ktds.football.dto.Request;
import com.ktds.football.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final PageService pageService;

    public void add(Request request) {
        requestRepository.add(request);
    }

    public List<Request> findPage(int currentPage) {
        return requestRepository.findPage(pageService.findStartPage(currentPage));
    }

    public PageDTO pagingParam(int currentPage, Long memberId) {

        int findByMemberIdCount = requestRepository.findByMemberIdCount(memberId);

        return pageService.pagingParam(currentPage, findByMemberIdCount);
    }
}
