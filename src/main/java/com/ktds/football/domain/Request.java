package com.ktds.football.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Request {

    private Long id;
    private Long memberId;
    private Long postId;
    private String position;
    private String status;
    private LocalDate createdAt;

}
