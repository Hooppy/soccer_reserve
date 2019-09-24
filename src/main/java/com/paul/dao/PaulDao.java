package com.paul.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.paul.model.PaulModel;
import com.paul.model.ReserveModel;

@Repository
public class PaulDao {
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	PaulModel paulModel;
	
	@Autowired
	private PasswordEncoder standardEncoder;
	
	private static String namespace = "com.paul.userMapper";
	
	public boolean isExist(String username) {
		
		int count = 0;
		
		count = sqlSession.selectOne(namespace+".isExist" , username);
		
		if(count > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void Join(PaulModel paulModel) throws Exception{
		
		String encPassword = standardEncoder.encode(paulModel.getPassword());
		
		paulModel.setPassword(encPassword);
		
		try {
			sqlSession.insert(namespace + ".join", paulModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PaulModel read(String username) throws Exception{
		try {
			paulModel = sqlSession.selectOne(namespace + ".read", username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paulModel;
	}
	
	public List<ReserveModel> getRsvrTime(ReserveModel reserveModel) throws Exception{
		List<ReserveModel> list = null;
		try {
			list = sqlSession.selectList(namespace + ".getRsvrTime", reserveModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void reserve(ReserveModel reserveModel) throws Exception{
		
		try {
			sqlSession.insert(namespace + ".reserve", reserveModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
