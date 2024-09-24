package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.TboardVo;

@Repository
public class TboardDao {

	@Autowired
	private SqlSession splSession;

	public List<TboardVo> selectList() {

		System.out.println("selectList");
		List<TboardVo> tboardList = splSession.selectList("tboard.selectList");
		System.out.println(tboardList.size());

		return tboardList;
	}

	public List<TboardVo> selectList2(Map<String, Integer> limitMap) {

		System.out.println("selectList2");
		List<TboardVo> tboardList = splSession.selectList("tboard.selectList2", limitMap);
		System.out.println(tboardList.size());

		return tboardList;
	}

	public int selectTotalCnt() {

		System.out.println("selectTotalCnt");
		int totalCount = splSession.selectOne("tboard.selectTotalCnt");
		System.out.println("totalCount"+totalCount);

		return totalCount;
	}
	
	public List<TboardVo> selectList3(Map<String, Object> limitMap) {

		System.out.println("selectList3");
		List<TboardVo> tboardList = splSession.selectList("tboard.selectList3", limitMap);
		System.out.println(tboardList.size());

		return tboardList;
	}
	
	public int selectTotalCntKeyword(String keyword) {

		System.out.println("selectTotalCntKeyword");
		int totalCount = splSession.selectOne("tboard.selectTotalCntKeyword", keyword);
		System.out.println("totalCount"+totalCount);

		return totalCount;
	}
}
