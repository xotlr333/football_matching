package com.ktds.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballApplication {

	// TODO : 비밀번호 암호화 (O)
	// TODO : 문의하기
	// TODO : 검색
	// TODO : 회원가입 시 아이디 중복 체크 (O)
	// TODO : 게시물 상태 마감인거 처리 (O)
	// TODO : 날짜 지난 게시물 표시 및 상태 변경하는 기능 추가 (O)
	// TODO : 카테고리 별로 게시물 나타내기 (O)
	// TODO : 목록 없을 시 다른 화면 출력 (O)
	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}

}
