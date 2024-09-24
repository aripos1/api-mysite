package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public int exeInsert(UserVo userVo) {
		int count = userDao.insertUser(userVo);
		return count;
	}

	/* 로그인 */
	public UserVo exeLogin(UserVo userVo) {

		UserVo authUser = userDao.selectUser(userVo);

		System.out.println("UserService.exeLogin()");
		System.out.println("로그인정보: " + userVo);

		return authUser;
	}

	/* 회원정보수정 */
	public int exeModify(UserVo userVo) {

		int count = userDao.updateUser(userVo);

		return count;
	}

	public UserVo exeModifyForm(int no) {
		System.out.println("exeModifyForm");
		UserVo modifyUserForm = userDao.modifyUserForm(no);
		System.out.println(modifyUserForm);

		return modifyUserForm;
	}
	
	
	/* 아이디 중복 체크*/
	public boolean exeDuplicate(String id) {
		System.out.println("UserService.exeDuplicate()");
		
		int count = userDao.selecUserById(id);
		
		if(count >= 1) {
			return false;
			
		}else {
			return true;
			
		}
		
		
	}
	
	
	
	
}
