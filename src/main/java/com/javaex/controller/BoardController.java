package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	// 필드

	// 생성자

	// 메서드 gs

	// 메서드 일반

	// 메인
	@RequestMapping(value = "/board/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardList(Model model) {

		System.out.println("boardList");

		List<BoardVo> boardList = boardService.exeBoardList();
		model.addAttribute("boardList", boardList);
		boardService.exeBoardList();

		return "board/list";
	}

	@RequestMapping(value = "/board/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardRead(@ModelAttribute BoardVo boardVo,HttpSession session,Model model) {
		System.out.println("UserController.login()");
		
		
		BoardVo boardOne = boardService.exeBoardOne(boardVo);
		model.addAttribute("BoardVo", boardOne);
		
		
		System.out.println(boardOne);
	

		return "board/read";
	}

	@RequestMapping(value = "/board/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeform() {

		return "board/writeForm";
	}

	@RequestMapping(value = "board/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(HttpSession session, @ModelAttribute BoardVo boardVo) {
		System.out.println("write");
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		int no = authUser.getNo();
		boardVo.setNo(no);

		boardService.exeInsert(boardVo);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/board/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(HttpSession session, @ModelAttribute BoardVo boardVo, @RequestParam int no) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int userNo = authUser.getNo();
		boardVo.setUserNo(userNo);
		boardVo.setNo(no);

		boardService.exeDelete(boardVo);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/board/modifyform", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyform(@ModelAttribute BoardVo boardVo,HttpSession session,Model model) {
		System.out.println("modifyform");
		
		BoardVo boardOne = boardService.exeBoardOne(boardVo);
		model.addAttribute("BoardVo", boardOne);
				
		System.out.println(boardOne);
		return "/board/modifyForm";
	}
	
	@RequestMapping(value = "board/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(HttpSession session, @ModelAttribute BoardVo boardVo) {
		System.out.println("modify");
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		int no = authUser.getNo();
		boardVo.setNo(no);

		boardService.exeUpdate(boardVo);

		return "redirect:/board/list";
	}
}
