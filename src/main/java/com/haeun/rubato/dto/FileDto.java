package com.haeun.rubato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
	
	private int filenum;			//파일의 고유번호(시퀀스)
	private int boardnum;			//파일이 첨부된 게시글의 번호
	private String filename;		//랜덤으로 변경된 파일의 이름
	private String orifilename;		//파일의 원래 이름
	private String fileurl;			//파일의 저장경로
	private String fileextension;	//파일의 확장자
}
