package com.haeun.rubato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
	private int rbnum;			//덧글 고유넘버 
	private int boardnum;		//덧글이 달린 게시글의 고유번호
	private String rbid;		//덧글을 쓴 유저의 아이디
	private String rbcontent;	//덧글 내용
	private String rbdate;		//덧글 쓴 날짜시간
}
