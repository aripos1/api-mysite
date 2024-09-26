package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
}
