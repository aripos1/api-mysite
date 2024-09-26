package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/* 로그인 */
	@PostMapping(value = "/api/users/login")
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {

		UserVo authUser = userService.exeLogin(userVo);

		if (authUser == null) {
			return JsonResult.fail("아이디가 없습니다.");
		} else {
			JwtUtil.createTokenAndSetHeader(response, "" + authUser.getNo());
			return JsonResult.success(authUser);
		}
	}

	/* 회원가입 */
	@PostMapping(value = "/api/users/join")
	public JsonResult join(@RequestBody UserVo userVo) {

		System.out.println(userVo);

		int count = userService.exeInsert(userVo);
		if (count != 1) {
			return JsonResult.fail("회원가입 실패");

		} else {
			return JsonResult.success(count);
		}
	}

	/* 중복체크 */
	@GetMapping(value = "/api/users/duplicate")
	public JsonResult duplicate(@RequestParam(value = "id") String id) {
		boolean count = userService.exeDuplicate(id);
		if (count == false) {
			return JsonResult.fail("중복입니다.");
		} else {
			return JsonResult.success(count);
		}
	}

	/* 회원정보가져오기 */
	@GetMapping("/api/users/me")
	public JsonResult modifyForm(HttpServletRequest request) {
		int no = JwtUtil.getNoFromHeader(request);
		if (no != -1) {
			// 정상
			UserVo userVo = userService.exeModifyForm(no);
			return JsonResult.success(userVo);
		} else {
			// 토큰이 없거나(로그인상태아님), 변조된 경우
			return JsonResult.fail("토큰X, 비로그인, 변조");
		}
	}

	@PutMapping("/api/users/me")
	public JsonResult update(@RequestBody UserVo userVo, HttpServletRequest request) {

		int no = JwtUtil.getNoFromHeader(request);
		userVo.setNo(no);

		int count = userService.exeModify(userVo);
		// 세션의 이름만 변경 (해더의 이름이 변경안됨 현상)
		userVo.setPassword(null);
		userVo.setGender(null);

		if (count == 1) {
			// 정상

			return JsonResult.success(userVo);
		} else {
			// 토큰이 없거나(로그인상태아님), 변조된 경우
			return JsonResult.fail("수정 실패");
		}
	}

}
