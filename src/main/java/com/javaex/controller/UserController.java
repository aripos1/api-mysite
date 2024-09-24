package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/api/mysites")
	public JsonResult join(@RequestBody UserVo userVo) {

		System.out.println(userVo);

		int count = userService.exeInsert(userVo);
		if (count != 1) {
			return JsonResult.fail("해당번호가 없습니다.");

		} else {
			return JsonResult.success(count);
		}
	}

	@RequestMapping(value = "user/joinok", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinok() {

		System.out.println("joinok");

		return "user/joinOk";
	}

	/* 로그인 */
	@PostMapping(value = "/api/mysites/login")
	public JsonResult login(@RequestBody UserVo userVo) {

		UserVo authUser = userService.exeLogin(userVo);
		System.out.println("세션에서 'no'와 '이름'을 잘 가져왔나" + authUser);

		if (authUser == null) {
			return JsonResult.fail("해당번호가 없습니다.");

		} else {
			return JsonResult.success(authUser);
		}
	}

	/* 로그아웃 */
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");

//		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/main";
	}

	@RequestMapping(value = "/user/modifyform", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyform(@ModelAttribute UserVo userVo, HttpSession session, Model model) {
		System.out.println("modifyform");
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		int no = authUser.getNo();

		UserVo modifyUser = userService.exeModifyForm(no);

		model.addAttribute("ModifyUser", modifyUser);
		/*
		 * System.out.println(authUser); session.setAttribute("ModifyUser", authUser);
		 * UserVo modifyUser = userService.exeModifyForm(authUser);
		 * System.out.println("ModifyUser " + modifyUser);
		 * model.addAttribute("ModifyUser", modifyUser);
		 */
		return "user/modifyForm";
	}

	@RequestMapping(value = "user/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("modify,update");

		// DS
		// userVo name pw gender no(X)

		// 세션의 no 가져오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int no = authUser.getNo();
		System.out.println(no);
		// userVo + no
		userVo.setNo(no);
		System.out.println(userVo);
		// vo를 서비스에 넘겨서 ㅂ수정 반영
		userService.exeModify(userVo);

		// 세션의 이름만 변경 (해더의 이름이 변경안됨 현상)

		authUser.setName(userVo.getName());

		return "redirect:/main";
	}

	@ResponseBody
	@RequestMapping(value = "api/user/duplicate", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean duplicate(@RequestParam(value = "id") String id) {
		System.out.println("UserController.idCheck()");
		boolean can = userService.exeDuplicate(id);
		System.out.println(id);
		return can;
	}
}
