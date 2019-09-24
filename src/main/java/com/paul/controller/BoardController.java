package com.paul.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paul.dao.BoardDao;
import com.paul.model.BoardModel;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	BoardModel boardModel;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String List(ModelMap model,
		@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
		@RequestParam(value = "amount", required = false, defaultValue = "10") int amount) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("pageNum", pageNum);
		map.put("amount", amount);
		
		List<BoardModel> boardList = boardDao.getBoardList(map);
		
		int total = boardDao.getTotalCnt();
		
		// Model 정보 저장
		model.addAttribute("boardList", boardList);
		model.addAttribute("total", total);
		model.addAttribute("page", map);
		
		return "board/board_list";
	}
	
	
	
	@RequestMapping("/write")
	public String Write(ModelMap model) {
		
		model.addAttribute("Write", "Write");
		
		return "board/board_write";
	}
	
	@RequestMapping("/writeComplete")
	public String WriteComp(ModelMap model,
		@RequestParam(value = "writer") String writer,
		@RequestParam(value = "title") String title,
		@RequestParam(value = "content") String content) {
		
		boardModel.setWriter(writer);
		boardModel.setTitle(title);
		boardModel.setContent(content);
		
		model.addAttribute("WriteComplete", "WriteComplete");
		
		try {
			boardDao.Insert(boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "board/board_list";
	}
}
