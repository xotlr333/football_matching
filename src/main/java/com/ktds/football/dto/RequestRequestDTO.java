package com.ktds.football.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRequestDTO {
	private Long memberId;
	private int startPostNum;
	private int perPage;
}
