package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	//리스트
	@RequestMapping(value="/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[GuestBookController.list]");
		
		GuestBookDao guestBookDao = new GuestBookDao();
		
		List<GuestBookVo> guestList = guestBookDao.guestbookList();
		
		model.addAttribute("gList",guestList);
		
		return "/WEB-INF/views/list.jsp";
	}
	
	//등록
	@RequestMapping(value="/insert", method = { RequestMethod.GET, RequestMethod.POST })
	public String insert(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("[GuestBookController.insert]");
		
		GuestBookDao guestBookDao = new GuestBookDao();
		guestBookDao.guestBookInsert(guestBookVo);
		
		return "redirect:/list";
	}
	
	//삭제폼
	@RequestMapping(value="/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(Model model,@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("[GuestBookController.deleteForm]");
		
		model.addAttribute("guestBookVo",guestBookVo);
		
		return "/WEB-INF/views/deleteForm.jsp";
	}
	
	//삭제
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("pw")String pw,@RequestParam("no")int no) {
		System.out.println("[GuestBookController.delete]");
		
		GuestBookDao guestBookDao = new GuestBookDao();
		
		guestBookDao.guestBookDelete(no,pw);
		
		return "redirect:/list";
	}
	
	
}
