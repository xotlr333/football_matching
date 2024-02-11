package com.ktds.football.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDTO {

	private Long memberId;
	private String prePassword;
	private String changePassword;
	private String changePasswordConfirm;
}
