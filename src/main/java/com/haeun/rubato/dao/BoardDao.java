package com.haeun.rubato.dao;

import java.util.ArrayList;
import java.util.List;

import com.haeun.rubato.dto.FBoardDto;
import com.haeun.rubato.dto.FileDto;
import com.haeun.rubato.dto.ReplyDto;

public interface BoardDao {
	public void fbWriteDao(String fbid, String fbtitle, String fbcontent);	//자유게시판 글쓰기
	public ArrayList<FBoardDto> fblistDao();	//자유게시판 글목록 가져오기
	public FBoardDto fbViewDao(String fbnum);	//자유게시판 글 내용 보기(fbnum으로 검색해서 글 하나만 가져오기)
	public void fbhitDao(String fbnum);			//자유게시판 조회수 증가 함수
	public ArrayList<FBoardDto> fbTitleSearchlist(String keyword);
	//자유게시판 제목으로 검색한 결과 리스트 가져오기
	public ArrayList<FBoardDto> fbContentSearchlist(String keyword);
	//자유게시판 내용에서 검색한 결과 리스트 가져오기
	public ArrayList<FBoardDto> fbNameSearchlist(String keyword);
	//자유게시판 글쓴이로 검색한 결과 리스트 가져오기
	
	//첨부 파일용 dao
	public void fbfileInsertDao(int boardnum, String filename, String orifilename, String fileurl, String fileextension);
	//파일 업로드(파일이 첨부된 게시글번호, 변경된 파일이름, 원본 파일이름, 파일저장경로, 파일의 확장자)
	public FileDto fbGetFileInfoDao(String boardnum);
	//파일이 첨부된 게시글의 번호로 검색하여 첨부된 파일의 정보
	
	//댓글 용 dao
	public void rbwriteDao(int boardnum, String rbid, String rbcontent);
	public ArrayList<ReplyDto> rblistDao(int fbnum);	//댓글이 달린 원글의 게시판 번호로 검색하여 모든 댓글리스트를 반환
}
