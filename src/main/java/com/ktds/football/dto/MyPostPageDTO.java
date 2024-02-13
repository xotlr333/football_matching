package com.ktds.football.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPostPageDTO {

    private Long memberId;
    private int startPostNum;
    private int perPage;

}
