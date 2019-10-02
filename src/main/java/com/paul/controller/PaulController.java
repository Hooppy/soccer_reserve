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
import com.paul.model.ReserveListModel;
import com.paul.model.ReserveModel;

@Controller
@RequestMapping("/user")
public class PaulController {
	
	@Autowired
	PaulDao paulDao;
	
	@Autowired
	PaulModel paulModel;
	
	@Autowired
	ReserveModel reserveModel;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		
		model.addAttribute("index", "index");
		
		return "user/index";
	}
	
	@RequestMapping("/new")
	public String newMember(ModelMap model) {
		
		model.addAttribute("new", "new");
		
		return "user/join";
	}
	
	@RequestMapping("/join")
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
		
		return "user/index";
	}
	
	@RequestMapping("/login")
	public String login(ModelMap model,
		@RequestParam(value = "username") String username,
		HttpSession session) {
			
		Object failureCode = session.getAttribute("failureCode");
		
		model.addAttribute("failureCode", failureCode);
		model.addAttribute("username", username);
		
		return "user/index";
	}
	
	@RequestMapping("/login_success")
	public String login_success(ModelMap model,
			HttpSession session) {
			
		Object failureCode = session.getAttribute("failureCode");
		
		model.addAttribute("msg", "로그인성공");
		
		return "user/login_success";
	}
	
	@RequestMapping("/reserve/search")
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
	
	@RequestMapping("/reserve")
	public String Reserve(ModelMap model,
		HttpSession httpSession,
		ReserveModel reserveModel) {
		
		String username = (String) httpSession.getAttribute("username");
		boolean isExistReserve = true;
		
		reserveModel.setReg_id(username);
		
		isExistReserve = paulDao.isExistReserve(reserveModel);
		
		try {
			if(!isExistReserve) {
				paulDao.reserve(reserveModel);
				model.addAttribute("msg", "예약성공");
			}else {
				model.addAttribute("msg", "예약실패 - 중복");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "user/login_success";
	}
	
	@RequestMapping("/reserve_list")
	public String Reserve(ModelMap model,
		HttpSession httpSession) {

		String username = (String) httpSession.getAttribute("reg_id");
		
		List<ReserveListModel> list = null;
		list = paulDao.getReserveList(username);
		
		model.addAttribute("msg", "예약조회");
		model.addAttribute("list", list);
		
		return "user/reserve_list";
	}
	
	@RequestMapping("/delete")
	public String Delete(ModelMap model,
		HttpSession httpSession,
		ReserveModel reserveModel) {

		String username = (String) httpSession.getAttribute("username");
		
		reserveModel.setReg_id(username);
		
		paulDao.delete(reserveModel);
		
		model.addAttribute("msg", "예약취소");
		
		return "user/login_success";
	}
}
