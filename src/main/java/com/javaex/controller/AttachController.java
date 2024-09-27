package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.AttachVo2;

import jakarta.servlet.http.HttpServletResponse;

@RestController // 이 클래스는 RESTful 웹 서비스의 컨트롤러임을 나타냄. JSON 응답을 반환함.
public class AttachController {

	@Autowired // Spring이 AttachService의 인스턴스를 자동으로 주입해줌.
	private AttachService attachService;

	// 파일 업로드 처리 메서드. 클라이언트(React)에서 "/api/attachs" 경로로 POST 요청이 오면 실행됨.
	@PostMapping("/api/attachs")
	public JsonResult upload(@RequestParam("profileImg") MultipartFile file, HttpServletResponse response) {

		// 업로드 과정 중 로그로 파일 업로드 시작을 알림.
		System.out.println("upload");

		// 업로드된 파일의 원래 파일 이름을 로그로 출력.
		System.out.println(file.getOriginalFilename());

		// 파일을 업로드하는 서비스 메서드를 호출하고, 파일이 저장된 경로 또는 파일명을 반환받음.
		String saveName = attachService.upload(file);

		// 파일 저장이 실패했거나 파일 이름이 없다면, 실패 메시지를 반환.
		if (saveName == null) {
			return JsonResult.fail("파일 업로드 실패. 아이디가 없습니다.");
		} else {
			// 파일이 정상적으로 업로드되었으면, JWT 토큰을 생성하고 응답 헤더에 추가.
			JwtUtil.createTokenAndSetHeader(response, "" + saveName);

			// 성공적으로 업로드된 파일 이름을 반환하며 성공 응답을 보냄.
			return JsonResult.success(saveName);
		}
	}

	@PostMapping("/api/attachs2")
	public JsonResult upload2(@ModelAttribute AttachVo2 attachVo2, @RequestParam("content") String content) {
	
		System.out.println(attachVo2);

		System.out.println(content);
		String saveName = attachService.upload2(attachVo2);
		return JsonResult.success(saveName);

	}
}
