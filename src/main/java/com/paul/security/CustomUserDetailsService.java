package com.paul.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.paul.dao.PaulDao;
import com.paul.model.PaulModel;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	PaulModel paulModel;
	
	@Autowired
	PaulDao paulDao;
	
	public PaulModel loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			//디비 정보를 불러와 유저정보 조회
			paulModel = paulDao.read(username);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paulModel;
	}
}