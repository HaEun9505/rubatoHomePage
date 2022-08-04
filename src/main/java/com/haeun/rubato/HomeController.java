package com.haeun.rubato;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haeun.rubato.dao.BoardDao;
import com.haeun.rubato.dao.MemberDao;
import com.haeun.rubato.dto.FBoardDto;

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
	public String board_list(HttpServletRequest request, Model model) {
		
		String searchKeyword = request.getParameter("searchKeyword");
		String searchOption = request.getParameter("searchOption");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		
		ArrayList<FBoardDto> fbDtos = null;
		
		if(searchKeyword == null) {	//검색한 단어가 없으면
			fbDtos = boardDao.fblistDao();	//전체 조회
		}else if(searchOption.equals("title")) {
			fbDtos = boardDao.fbTitleSearchlist(searchKeyword);	//검색한 값 조회
		}else if(searchOption.equals("content")) {
			fbDtos = boardDao.fbContentSearchlist(searchKeyword);
		}else if(searchOption.equals("writer")) {
			fbDtos = boardDao.fbNameSearchlist(searchKeyword);
		}
		
		//게시판 글목록의 글 개수
		int listCount = fbDtos.size();
		
		//데이터를 model 객체에 실어서 전달
		model.addAttribute("fblist", fbDtos);
		model.addAttribute("listCount", listCount);
		
		return "board_list";
	}
	
	@RequestMapping(value="/board_view")
	public String board_view(HttpServletRequest request, Model model) {
		
		String fbnum = request.getParameter("fbnum");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		boardDao.fbhitDao(fbnum);	//조회수 증가 함수 호출
		
		//FBoardDto를 반환
		FBoardDto fboardDto = boardDao.fbViewDao(fbnum);
		
		model.addAttribute("fbView", fboardDto);
		
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
	
	@RequestMapping(value = "/board_writeOk", method = RequestMethod.POST)
	public String board_writeOk(HttpServletRequest request) {
		
		String fbtitle = request.getParameter("fbtitle");
		String fbcontent = request.getParameter("fbcontent");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

		//세션에 저장한 값 가져오기
		HttpSession session = request.getSession();
		
		String fbid = (String) session.getAttribute("sessionId");
		
		if(fbid == null) {	//로그인이 안되어있으면
			fbid = "GUEST";
		}
		
		boardDao.fbWriteDao(fbid, fbtitle, fbcontent);
		
		return "redirect:list";
	}
}
