package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	//리스트
	@RequestMapping(value = "/guestbook/addlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {

		List<GuestVo> exeGuestList = guestbookService.exeGuestList();
		model.addAttribute("guestList", exeGuestList);
	

		return "guestbook/addList";
	}
	
	@RequestMapping(value = "guestbook/insert", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute GuestVo guestVo) {
		int count = guestbookService.exeWrite(guestVo);
		System.out.println(count);
		return "redirect:/guestbook/addlist";
	}

	//삭제폼
	@RequestMapping(value = "/guestbook/deleteform", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm() {
		System.out.println("삭제폼 요청");
		// deleteForm.jsp로 이동
		return "guestbook/deleteForm";
	}
	@RequestMapping(value = "/guestbook/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		System.out.println("delete 요청");
		// 데이터베이스에서 삭제 처리
		boolean isDeleted = guestbookService.exeDelete(no, password);		
		
		if (isDeleted) {
			System.out.println("삭제 성공");
			// 삭제 성공 시 리스트 페이지로 리다이렉트
			return "redirect:/guestbook/addlist";
		} else {
			System.out.println("삭제 실패");
			return "guestbook/deleteForm";
		}

	}
	@RequestMapping(value = "/guestbook/ajaxindex", method = { RequestMethod.GET, RequestMethod.POST })
	public String ajaxindex() {
		
		System.out.println("ajaxindex");
			
		return "/guestbook/ajaxindex";
	}
	
	
}
