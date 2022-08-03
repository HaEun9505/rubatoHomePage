package com.haeun.rubato.dao;

public interface MemberDao {
	
	public void memberJoinDao(String mid, String mpw, String mname, String memail);	//회원가입
	public int checkIdDao(String mid);	//회원아이디 DB 존재 여부 체크(존재하면 1, 없으면 0 반환)
	public int checkPwDao(String mid, String mpw);	//회원아이디&비밀번호 DB 존재 여부 체크(존재하면 1, 없으면 0 반환)
}
