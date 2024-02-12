package com.ktds.football.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRequestDTO {
	private Long id;
	private Long memberId;
	private int startPostNum;
	private int perPage;
	private Long postId;
}
