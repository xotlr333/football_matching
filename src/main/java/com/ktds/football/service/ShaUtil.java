package com.ktds.football.service;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

@Service
public class ShaUtil {

	public String sha256Encode(String text) {
		return Hashing.sha256()
			.hashString(text, StandardCharsets.UTF_8)
			.toString();
	}
}
