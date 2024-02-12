package com.ktds.football.repository;

import com.ktds.football.domain.Request;
import com.ktds.football.dto.RequestRequestDTO;
import com.ktds.football.dto.RequestResponseDTO;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestRepository {

    private final SqlSessionTemplate sql;

    public void add(Request request) {
        sql.insert("Request.add", request);
    }

    public List<RequestResponseDTO> findPage(RequestRequestDTO requestDTO) {
        return sql.selectList("Request.findPage", requestDTO);
    }

    public int findByMemberIdCount(Long memberId) {
        return sql.selectOne("Request.findByMemberIdCount", memberId);
    }

    public void delete(Long requestId) {
        sql.delete("Request.delete", requestId);
    }

	public List<RequestResponseDTO> findPageOfPost(RequestRequestDTO requestDTO) {
        return sql.selectList("Request.findPageOfPost", requestDTO);
	}

    public int findByPostIdCount(Long postId) {
        return sql.selectOne("Request.findByPostIdCount", postId);
    }

    public void refuse(Long id) {
        sql.update("Request.refuse", id);
    }

    public int findApproveByPostIdCount(Long postId) {
        return sql.selectOne("Request.findApproveByPostIdCount", postId);
    }
}
