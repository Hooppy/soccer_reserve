package com.paul.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.paul.dao.PaulDao;
import com.paul.model.PaulModel;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	PaulDao paulDao;
	
	@Autowired
	PaulModel paulModel;

	@Autowired
	private PasswordEncoder standardEncoder;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//유저가 입력한 정보를 이이디 비번으로만든다. (로그인한 유저아이디 비번정보를 담는다)
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication; 
		
		System.out.println(authToken.getName());
		
		paulModel = customUserDetailsService.loadUserByUsername(authToken.getName()); //UserDetailsService에서 유저정보를 불러온다.
		
		if(!paulDao.isExist(authToken.getName())) {
			System.out.println("1");
			throw new BadCredentialsException("Non Exist");
		}else {
	    	
	    	// 암호화 되지 않은 authToken.getCredentials(id입력값)과 암호화된 testModel.getPassword(DB에 저장된 값)을 비교하여 맞으면 true
	    	if(!standardEncoder.matches((String) authToken.getCredentials(), paulModel.getPassword())) {
	    		System.out.println("2");
	    		throw new BadCredentialsException("Not Match Password");
		    }
	    	
	    	authorities = (List<GrantedAuthority>) paulModel.getAuthorities();
	    }
		
	    return new UsernamePasswordAuthenticationToken(paulModel, null, authorities);
	}
	
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}

