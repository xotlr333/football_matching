package com.ktds.football.service;

import com.ktds.football.dto.Post;
import com.ktds.football.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Post> findAll() {
        return boardRepository.findAll();
    }
}
