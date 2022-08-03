package com.haeun.rubato;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haeun.rubato.dao.MemberDao;

@Controller
public class HomeController {
	
	@Autowired	//외부에서 객체를 생성해서 자동주입
	
	private SqlSession sqlSession;
	
	@RequestMapping(value="/")
	public String root() {
		return "index";
	}
	
	@RequestMapping(value="/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/board_list")
	public String board_list() {
		return "board_list";
	}
	
	@RequestMapping(value="/board_view")
	public String board_view() {
		return "board_view";
	}
	
	@RequestMapping(value="/board_write")
	public String board_write() {
		return "board_write";
	}
	
	@RequestMapping(value="/member_join")
	public String member_join() {
		
		return "member_join";
	}
	
	@RequestMapping(value="/member_joinOk", method = RequestMethod.POST)
	public String member_joinOk(HttpServletRequest request, Model model) {
		
		//request 객체로 값 빼오기
		String memberid = request.getParameter("mid");
		String memberpw = request.getParameter("mpw");
		String membername = request.getParameter("mname");
		String memberemail = request.getParameter("memail");
		
		//dao 호출하면 sqlSession과 연결됨
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		memberDao.memberJoinDao(memberid, memberpw, membername, memberemail);
		
		//세션에 저장
		HttpSession session = request.getSession();
		
		session.setAttribute("sessionId", memberid);
		session.setAttribute("sessionName", membername);
		
		//데이터를 model 객체에 실어서 전달
		model.addAttribute("memberid", memberid);
		model.addAttribute("membername", membername);
		
//		String sid = (String)session.getAttribute("sessionId");
//		String sname = (String)session.getAttribute("sessionName");
//		
//		//데이터를 model 객체에 실어서 전달
//		model.addAttribute("memberid", sid);
//		model.addAttribute("membername", sname);
		
		return "redirect:index";	//요청이 새로 들어옴(새로고침)
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		//세션 무효화
		session.invalidate();	//세션 삭제->로그아웃		
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/member_loginOk", method = RequestMethod.POST)
	public String member_loginOk(HttpServletRequest request, Model model) {
		
		String memberid = request.getParameter("mid");
		String memberpw = request.getParameter("mpw");
		
		//dao 호출하면 sqlSession과 연결됨
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		int checkIdValue = memberDao.checkIdDao(memberid);
		//DB에 아이디가 존재하면 1 반환, 없으면 0 반환
		int checkPwValue = memberDao.checkPwDao(memberid, memberpw);
		//DB에 아이디와 비밀번호가 일치하는 계정이 존재하면 1 반환, 없으면 0 반환 
		
		//데이터를 model객체에 실어서 전달
		model.addAttribute("checkIdValue", checkIdValue);
		model.addAttribute("checkPwValue", checkPwValue);
		
		if(checkPwValue == 1) {	//id,pw 둘 다 존재하면
			
			//세션에 저장
			HttpSession session = request.getSession();
			
			session.setAttribute("sessionId", memberid);
			
		}
		return "loginOk";
	}
}
