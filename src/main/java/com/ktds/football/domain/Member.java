package com.ktds.football.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Member {

    private Long id;
    private String name;
    private String loginId;
    private String password;
    private LocalDate createdAt;

}
