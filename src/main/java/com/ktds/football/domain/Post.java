package com.ktds.football.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {

    private Long id;
    private Long memberId;
    private Long categoryId;
    private String title;
    private String content;
    private String address;
    private int people;
    private String skill;
    private LocalDateTime gameDate;
    private String position;
    private String status;
    private LocalDateTime createdAt;

}
