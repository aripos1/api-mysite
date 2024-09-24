package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertUser(UserVo userVo) {

		int count = sqlSession.insert("user.insert", userVo);

		return count;

	}

	/* 로그인 */
	public UserVo selectUser(UserVo userVo) {

		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		System.out.println(authUser + "이것은 디비가 다오에게 주는 거");

		return authUser;

	}

	/* 회원 정보 수정하기 */
	public int updateUser(UserVo userVo) {

		int count = sqlSession.update("user.updateUser", userVo);

		return count;
	}

	public UserVo modifyUserForm(int no) {
		System.out.println("modifyUser");
		System.out.println(no);
		UserVo modifyUserForm = sqlSession.selectOne("user.selectModify", no);
		System.out.println(modifyUserForm);

		return modifyUserForm;
	}

	//id로 데이터가져오기-id사용여부 체크할때 사용
	public int selecUserById(String id) {
		System.out.println("UserDao.selecUserById()");
		
		int count = sqlSession.selectOne("user.selectById", id );
		return count;
	}
	
	
}
