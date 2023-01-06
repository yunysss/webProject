package com.br.member.model.service;

import java.sql.Connection;

import static com.br.common.JDBCTemplate.*;
import com.br.member.model.dao.MemberDao;
import com.br.member.model.vo.Member;

public class MemberService {

	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = getConnection();
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		close(conn);
		return m;
	}
	
	/**
	 * 회원가입요청 서비스
	 * @author 예서
	 * @param m insert할 데이터들이 담겨있는 Member객체
	 * @return insert 후에 처리된 행 수
	 */
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public Member updateMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		if(result > 0) {
			commit(conn);
			// db에는 정보변경이 되었지만 session에 담긴 회원객체는 로그인시 담았던 그대로임
			// 갱신된 회원 객체 다시 조회해오기 => 세션에 담긴 회원객체를 갱신시켜야하기 때문
			// 회원 조회하는 sql문 이미 있지만 비밀번호는 담아오지 않았기 때문에 새로 만들어야함
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem; // null일 경우 변경 실패
	}
	
	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		
		Member updateMem = null;
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		if(result > 0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
		
	}
	
	public int deleteMember(String userId, String userPwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
