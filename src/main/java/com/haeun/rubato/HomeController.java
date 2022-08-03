package com.haeun.rubato;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
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
}
