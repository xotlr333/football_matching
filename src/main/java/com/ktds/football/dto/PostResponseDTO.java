package com.ktds.football.dto;

import com.ktds.football.domain.PostStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {

    private Long id;
    private Long memberId;
    private Long categoryId;
    private String categoryTitle;
    private String title;
    private String content;
    private String address;
    private int people;
    private String skill;
    private LocalDateTime gameDate;
    private String position;
    private PostStatus status;
    private LocalDateTime createdAt;
}
