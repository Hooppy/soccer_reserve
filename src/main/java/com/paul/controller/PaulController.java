package com.paul.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paul.dao.PaulDao;
import com.paul.model.PaulModel;
import com.paul.model.ReserveModel;

@Controller
public class PaulController {
	
	@Autowired
	PaulDao paulDao;
	
	@Autowired
	PaulModel paulModel;
	
	@Autowired
	ReserveModel reserveModel;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		
		model.addAttribute("index", "index");
		
		return "index";
	}
	
	@RequestMapping("new")
	public String newMember(ModelMap model) {
		
		model.addAttribute("new", "new");
		
		return "join";
	}
	
	@RequestMapping("join")
	public String Join(ModelMap model,
		@RequestParam(value = "username") String username,
		@RequestParam(value = "password") String password) {
		
		paulModel.setUsername(username);
		paulModel.setPassword(password);
		
		try {
			paulDao.Join(paulModel);
		} catch (Exception e) {
			model.addAttribute("fail", "회원가입 실패");
			e.printStackTrace();
		}
		
		model.addAttribute("join", "join");
		
		return "index";
	}
	
	@RequestMapping("login")
	public String login(ModelMap model,
			HttpSession session) {
			
		Object failureCode = session.getAttribute("failureCode");
		
		model.addAttribute("failureCode", failureCode);
		
		return "index";
	}
	
	@RequestMapping("login_success")
	public String login_success(ModelMap model,
			HttpSession session) {
			
		Object failureCode = session.getAttribute("failureCode");
		
		model.addAttribute("msg", "로그인성공");
		
		return "login_success";
	}
	
	@RequestMapping("reserve/search")
	@ResponseBody
	public List<ReserveModel> ReserveSearch(ModelMap model,
		ReserveModel reserveModel) {
		
		System.out.println("CODE : " + reserveModel.getCode() + " TIME : " + reserveModel.getTime());
		
		List<ReserveModel> list = null;
		
		try {
			list = paulDao.getRsvrTime(reserveModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		
		System.out.println("LIST : " + list);
		
		return list;
	}
	
	@RequestMapping("reserve")
	public String Reserve(ModelMap model,
		ReserveModel reserveModel) {
		
		try {
			paulDao.reserve(reserveModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		model.addAttribute("msg", "예약성공");
		
		return "login_success";
	}
}
