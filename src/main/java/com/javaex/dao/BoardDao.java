package com.javaex.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 리스트 불러오기

	public List<BoardVo> getBoardList() {

		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		System.out.println(boardList);
		return boardList;

	}

	public BoardVo getBoardOne(BoardVo boardVo) {

		BoardVo boardOne = sqlSession.selectOne("board.selectOne", boardVo);
		System.out.println(boardOne);
		return boardOne;

	}
	
	public int getInsert(BoardVo boardVo) {
		
		if (boardVo.getRegDate() == null) {
			boardVo.setRegDate(LocalDateTime.now());
		}
		int count = sqlSession.insert("board.insert",boardVo);
		System.out.println(count);
		return count;
	}

	public int getDelete(BoardVo boardVo) {
		
		int count = sqlSession.delete("board.delete",boardVo);
		System.out.println(count);
		return count;
	}
	
	public int getUpdate(BoardVo boardVo) {
		
		int count = sqlSession.update("board.update",boardVo);
		System.out.println(count);
		return count;
	}
}
