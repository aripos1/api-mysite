package com.javaex.vo;

import java.time.LocalDateTime;

public class GuestVo {

	private int no;
	private String name;
	private String password;
	private String content;
	private LocalDateTime regDate;
	private String date;

	public GuestVo() {
		super();
	}

	public GuestVo(int no) {
		super();
		this.no = no;

	}

	public GuestVo(int no, String password) {
		super();
		this.no = no;
		this.password = password;
	}

	public GuestVo(String name, String password, String content) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = LocalDateTime.now();
	}

	public GuestVo(int no, String name, String content, LocalDateTime regDate) {
		super();
		this.no = no;
		this.name = name;
		this.content = content;
		this.regDate = LocalDateTime.now();
	}

	public GuestVo(int no, String name, String content, String date) {
		super();
		this.no = no;
		this.name = name;
		this.content = content;
		this.date = date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "GuestVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content + ", regDate="
				+ regDate + "]";
	}

}