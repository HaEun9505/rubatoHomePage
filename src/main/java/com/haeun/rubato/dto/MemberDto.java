package com.haeun.rubato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor	//인수가 없는 생성자
@AllArgsConstructor	//인수가 있는 생성자
public class MemberDto {
	
	private String mid;
	private String mpw;
	private String mname;
	private String memail;
	
}
