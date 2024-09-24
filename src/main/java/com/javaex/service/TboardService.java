package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {

	@Autowired
	private TboardDao tboardDao;

	public List<TboardVo> exeList() {

		System.out.println("exeList");
		List<TboardVo> tboardList = tboardDao.selectList();

		return tboardList;
	}

	public Map<String, Object> exeList2(int crtPage) {

		System.out.println("exeList2");
		System.out.println(crtPage);

		int listCnt = 10; // 한페이지 노출되는 게시물 개수
		
		//3항 연산자
//		crtPage = (crtPage>0) ?crtPage: (crtPage=1);
		if(crtPage<0) {
			crtPage =1;
		}
		
		int totalCnt = tboardDao.selectTotalCnt();

		// startRowNo 구하기
		// 1(0-10) 2(10-10) 3(20-10) 4(30-10)
		// (1-1)10 10

		// startRowNo = (crtPage-1)listCnt

		int startRowNo = (crtPage - 1) * listCnt;
		System.out.println(startRowNo);

		// 두개의 데이타를 1개로 묶어준다

		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("listCnt", listCnt);
		limitMap.put("startRowNo", startRowNo);
		System.out.println(limitMap);
		
		List<TboardVo> tboardList = tboardDao.selectList2(limitMap);
		// 페이지당 버튼 개수
		int pageBtnCount = 5;

		// endPageBtnNo 구하기
		// 1,2,3,4,5(1-5) 6,7,8,9,10(6-10)

		int endPageBtnNo = (int) (Math.ceil(crtPage / (double) pageBtnCount) * pageBtnCount);
		System.out.println("endPageBtnNo" + endPageBtnNo);

		// 시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtnCount+1);
		System.out.println("startPageBtnNo" + startPageBtnNo);

		// 다음 화살표 유무
		boolean next = false;
		if (endPageBtnNo * listCnt < totalCnt) {
			next = true;
		}else {
			// 다음화살표가 false 일때 마지막 숫자 버튼이 개수를 정확히 계산
			endPageBtnNo = (int)(Math.ceil(totalCnt/(double)listCnt));
		}
		
		boolean prev = false;
		if (startPageBtnNo >2) {
			prev = true;
		}
		
		//화면의 모든 데이터를 묶는다 map
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("tboardList", tboardList);
		pMap.put("prev",prev);
		pMap.put("next",next);
		pMap.put("endPageBtnNo",endPageBtnNo);
		pMap.put("startPageBtnNo",startPageBtnNo);
		return pMap;
	}

	public Map<String, Object> exeList3(int crtPage, String keyword) {


		int listCnt = 10; // 한페이지 노출되는 게시물 개수
		
		//3항 연산자
//		crtPage = (crtPage>0) ?crtPage: (crtPage=1);
		if(crtPage<0) {
			crtPage =1;
		}
		
		int totalCnt = tboardDao.selectTotalCntKeyword(keyword);
		System.out.println(totalCnt);
		// startRowNo 구하기
		// 1(0-10) 2(10-10) 3(20-10) 4(30-10)
		// (1-1)10 10

		// startRowNo = (crtPage-1)listCnt

		int startRowNo = (crtPage - 1) * listCnt;
		System.out.println(startRowNo);

		// 두개의 데이타를 1개로 묶어준다

		Map<String, Object> limitMap = new HashMap<String, Object>();
		limitMap.put("listCnt", listCnt);
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("keyword", keyword);
		
		List<TboardVo> tboardList = tboardDao.selectList3(limitMap);
		// 페이지당 버튼 개수
		int pageBtnCount = 5;

		// endPageBtnNo 구하기
		// 1,2,3,4,5(1-5) 6,7,8,9,10(6-10)

		int endPageBtnNo = (int) (Math.ceil(crtPage / (double) pageBtnCount) * pageBtnCount);
		System.out.println("endPageBtnNo" + endPageBtnNo);

		// 시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtnCount+1);
		System.out.println("startPageBtnNo" + startPageBtnNo);

		// 다음 화살표 유무
		boolean next = false;
		if (endPageBtnNo * listCnt < totalCnt) {
			next = true;
		}else {
			// 다음화살표가 false 일때 마지막 숫자 버튼이 개수를 정확히 계산
			endPageBtnNo = (int)(Math.ceil(totalCnt/(double)listCnt));
		}
		
		boolean prev = false;
		if (startPageBtnNo >2) {
			prev = true;
		}
		
		//화면의 모든 데이터를 묶는다 map
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("tboardList", tboardList);
		pMap.put("prev",prev);
		pMap.put("next",next);
		pMap.put("endPageBtnNo",endPageBtnNo);
		pMap.put("startPageBtnNo",startPageBtnNo);
		return pMap;
	}
}
