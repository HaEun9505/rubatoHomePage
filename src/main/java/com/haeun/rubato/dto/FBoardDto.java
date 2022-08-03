package com.haeun.rubato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FBoardDto {
	private int fbnum;			//게시판 번호
	private String fbid;		//게시판 글쓴 아이디
	private String fbcontent;	//게시판 글쓴 내용
	private String fbtitle;		//게시판 글 제목
	private String fbdate;		//게시판 글쓴 날짜
	private String fbhit;		//조회수
	private int fbreplycount;	//해당 게시글의 덧글 개수
	
}
