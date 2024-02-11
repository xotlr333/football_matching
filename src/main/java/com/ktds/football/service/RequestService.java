package com.ktds.football.service;


import com.ktds.football.dto.PageDTO;
import com.ktds.football.domain.Request;
import com.ktds.football.dto.RequestRequestDTO;
import com.ktds.football.dto.RequestResponseDTO;
import com.ktds.football.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<RequestResponseDTO> findPage(int currentPage, Long memberId) {

        Map<String, Integer> map = pageService.findStartPage(currentPage);
        RequestRequestDTO requestDTO = new RequestRequestDTO();
        requestDTO.setMemberId(memberId);
        requestDTO.setStartPostNum(map.get("startPostNum"));
        requestDTO.setPerPage(map.get("perPage"));

        return requestRepository.findPage(requestDTO);
    }

    public PageDTO pagingParam(int currentPage, Long memberId) {

        int findByMemberIdCount = requestRepository.findByMemberIdCount(memberId);

        return pageService.pagingParam(currentPage, findByMemberIdCount);
    }

    public void delete(Long requestId) {
        requestRepository.delete(requestId);
    }
}
