package com.paul.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paul.dao.PaulDao;
import com.paul.model.PaulModel;
import com.paul.model.ReserveListModel;
import com.paul.model.ReserveModel;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	PaulDao paulDao;
	
	@Autowired
	PaulModel paulModel;
	
	@Autowired
	ReserveModel reserveModel;
	
	@RequestMapping("/index")
	public String adminIndex(ModelMap model,
		HttpSession session) {
			
		Object failureCode = session.getAttribute("failureCode");
		
		model.addAttribute("failureCode", failureCode);
		
		return "admin/index";
	}
	
	@RequestMapping("/login")
	public String adminLogin(ModelMap model,
		HttpSession session) {
			
		Object failureCode = session.getAttribute("failureCode");
		
		model.addAttribute("failureCode", failureCode);
		
		return "admin/index";
	}
	
	@RequestMapping("/login_success")
	public String adminLogin_success(ModelMap model,
			HttpSession session) {
		
		List<ReserveListModel> list = null;
		
		list = paulDao.getReserveList(null);
		
		model.addAttribute("msg", "관리자로그인 성공");
		model.addAttribute("list", list);
		
		return "admin/login_success";
	}
}
