package com.haeun.rubato;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.haeun.rubato.dao.BoardDao;
import com.haeun.rubato.dao.MemberDao;
import com.haeun.rubato.dto.FBoardDto;
import com.haeun.rubato.dto.FileDto;

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
		int fbnumint = Integer.parseInt(fbnum);
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		boardDao.fbhitDao(fbnum);	//조회수 증가 함수 호출
		
		//FBoardDto를 반환
		FBoardDto fboardDto = boardDao.fbViewDao(fbnum);
		FileDto fileDto = boardDao.fbGetFileInfoDao(fbnum);
		
		//데이터를 model객체에 실어서 보냄
		model.addAttribute("fbView", fboardDto);
		model.addAttribute("fileDto", fileDto);
		model.addAttribute("rblist", boardDao.rblistDao(fbnumint));	//댓글리스트 가져와서 반환하기
		
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
	public String board_writeOk(HttpServletRequest request,@RequestPart MultipartFile uploadfiles)throws Exception {
		//@RequestPart - MultipartFile을 쓰기 위해 어노테이션
		String fbtitle = request.getParameter("fbtitle");
		String fbcontent = request.getParameter("fbcontent");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

		//세션에 저장한 값 가져오기
		HttpSession session = request.getSession();
		
		String fbid = (String) session.getAttribute("sessionId");
		
		if(fbid == null) {	//로그인이 안되어있으면
			fbid = "GUEST";
		}
		
		//파일에 대한 정보 출력
		if(uploadfiles.isEmpty()) {	//파일 첨부 여부를 판단(true or false)
			boardDao.fbWriteDao(fbid, fbtitle, fbcontent);	//이 메소드가 실행되자마자 최근 글을 불러옴(첫번째글이 됨)
			//첨부된 파일이 없는 경우 제목과 내용만 DB에 업로드
		}else {
			boardDao.fbWriteDao(fbid, fbtitle, fbcontent);
			ArrayList<FBoardDto> fbDtos = boardDao.fblistDao();
			
			String orifilename = uploadfiles.getOriginalFilename();	//원래 파일의 이름 가져오기
			String fileextension = FilenameUtils.getExtension(orifilename).toLowerCase();
			//확장자 가져오기(소문자로 변환)
			String fileurl = "D:\\SpringBoot_workspace\\rubatoHomePage\\src\\main\\resources\\static\\uploadfiles\\";
			String filename;//변경된 파일의 이름(서버에 저장되는 파일의 이름)
			File desinationFile;	//java.io의 파일관련 클래스
			
			do {
			filename = RandomStringUtils.randomAlphanumeric(32) + "." + fileextension;
			//영문대소문자와 숫자가 혼합된 랜덤 32자의 파일이름을 생성한 후 확장자 연결하여 서버에 저장될 파일의 이름 생성
			
			//저장경로 + 변경된 파일 이름
			desinationFile = new File(fileurl + filename);	//최종 파일 경로
			}while(desinationFile.exists());	//같은 이름의 파일이 저장소에 존재하면 true 출력
			
			//갖고 온 경로를 새로운 디렉터리 생성 후 옮김
			desinationFile.getParentFile().mkdir();
			uploadfiles.transferTo(desinationFile);
			
			int boardnum = fbDtos.get(0).getFbnum();	
			//가져온 게시글 목록 중에서 가장 최근에 만들어진 글이 불러와짐
			boardDao.fbfileInsertDao(boardnum, filename, orifilename, fileurl, fileextension);
		}
		
		return "redirect:board_list";
	}
	
	@RequestMapping(value = "/replyOk")
	public String replyOk(HttpServletRequest request, Model model) {
		
		String boardnum = request.getParameter("boardnum");//댓글이 달릴 원 게시글의 고유번호
		String rbcontent = request.getParameter("rbcontent");//덧글의 내용
		int fbnum = Integer.parseInt(boardnum);
		
		//세션에 저장한 값 가져오기
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionId");
		String rbid = null;
		
		if(sessionId == null) {	//로그인이 안되어있으면
			rbid = "GUEST";		//게스트
		}else {
			rbid = sessionId;
		}
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		
		boardDao.rbwriteDao(fbnum, rbid, rbcontent);
		
		//원 글 내용
		model.addAttribute("fbView", boardDao.fbViewDao(boardnum));	//자유게시판 글 내용 보기
		//덧글내용
		model.addAttribute("rblist", boardDao.rblistDao(fbnum));
		return "board_view";
	}
}
