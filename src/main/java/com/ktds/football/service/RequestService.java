package com.ktds.football.service;


import com.ktds.football.dto.Request;
import com.ktds.football.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    public void add(Request request) {
        requestRepository.add(request);
    }
}
