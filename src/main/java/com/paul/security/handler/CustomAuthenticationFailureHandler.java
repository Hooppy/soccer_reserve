package com.paul.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


// https://zgundam.tistory.com/53?category=430446 - 참고
// extends SimpleUrlAuthenticationFailureHandler 1가지 가능
// implements AuthenticationFailureHandler 1가지 가능
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private String failureCode = "";
	
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		System.out.println("exception : "  + exception);
				
		if(exception instanceof AuthenticationServiceException || exception.getCause() instanceof AuthenticationServiceException) {
			
			failureCode = "존재하지 않는 ID";
			
		} else if(exception instanceof BadCredentialsException || exception.getCause() instanceof BadCredentialsException) {
			
			failureCode = "일치하지 않는 비밀번호";
			
		}
		
		session.setAttribute("failureCode", failureCode);
		
		super.setDefaultFailureUrl("/index");
		
		super.onAuthenticationFailure(request, response, exception);
	}
}
