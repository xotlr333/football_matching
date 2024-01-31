package com.ktds.football.service;

import com.ktds.football.dto.User;
import com.ktds.football.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public int join(User user) {
        return userRepository.save(user);
    }
}
