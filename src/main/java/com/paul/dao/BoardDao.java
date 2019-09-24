package com.paul.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.paul.model.BoardModel;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	BoardModel boardModel;
	
	private static String namespace = "com.paul.boardMapper";
	
	public void Insert(BoardModel boardModel) throws Exception{
		
		try {
			sqlSession.insert(namespace + ".write", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardModel> getBoardList(Map map){
		
		List<BoardModel> list = null;
		System.out.println(map.get("pageNum"));
		try {
			list = sqlSession.selectList(namespace + ".list", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getTotalCnt() {
		int cnt = 0;
		try {
			cnt = sqlSession.selectOne(namespace + ".cnt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
