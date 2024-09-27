package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@GetMapping("/api/guestbooks")
	public List<GuestVo> getList() {
		
		List<GuestVo> guestList = guestbookService.exeGuestList();
		
		System.out.println(guestList);
		return guestList;
	}
	
	@PostMapping("/api/guestbooks")
	public String write(@RequestBody GuestVo geustVo) {

//		int count = phonebookDao.insertPerson(personVo);
		guestbookService.exeWrite(geustVo);
//		System.out.println(count);

		return "redirect:/";
	}
	@GetMapping("/api/guestbooks/{no}")
	public GuestVo getGuestOne(@PathVariable(value = "no") int no) {

		GuestVo guestVo = guestbookService.exeDeleteForm(no);

		System.out.println(guestVo);
		return guestVo;
	}

	@DeleteMapping("/api/guestbooks/{no}")
	public boolean delete(@PathVariable(value = "no") int no, @RequestBody GuestVo guestVo) {
		String password = guestVo.getPassword(); // 요청에서 비밀번호 가져오기
		boolean deleted = guestbookService.exeDelete(no, password);
		return deleted;
	}
	
//	/* 회원정보가져오기 */
//	@GetMapping("/api/users/me")
//	public JsonResult modifyForm(HttpServletRequest request) {
//		int no = JwtUtil.getNoFromHeader(request);
//		if (no != -1) {
//			// 정상
//			UserVo userVo = userService.exeModifyForm(no);
//			return JsonResult.success(userVo);
//		} else {
//			// 토큰이 없거나(로그인상태아님), 변조된 경우
//			return JsonResult.fail("토큰X, 비로그인, 변조");
//		}
//	}
	
}
