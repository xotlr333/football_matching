package com.ktds.football.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestResponseDTO {
	private Long id;
	private Long postId;
	private String title;
	private LocalDate gameDate;
	private String position;
	private String status;
}
