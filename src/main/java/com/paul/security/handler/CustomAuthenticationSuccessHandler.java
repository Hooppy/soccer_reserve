package com.paul.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.paul.dao.PaulDao;

// https://zgundam.tistory.com/52?category=430446 - ����
// extends SimpleUrlAuthenticationSuccessHandler, SavedRequestAwareAuthenticationSuccessHandler 2���� ����
// implements AuthenticationSuccessHandler 1���� ����
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	PaulDao paulDao;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	// ��û����(request), ��������(response), ��������(authentication)�� �����´�.
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		
		String url = "";
		
		HttpSession httpSession = request.getSession();
		
		//Session�� �����ؼ� Controller���� Ȱ��
		httpSession.setAttribute("username", request.getParameter("username"));
		httpSession.setAttribute("password", request.getParameter("password"));
		
		RequestCache requestCache = new HttpSessionRequestCache();
		
		// spring-security Ÿ�� ���� URL �� ����
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		// spring-security Ÿ�� ���� URL �� �����ؼ� Redirect (login ������, login ȭ�� ���� �Է��� url�� �̵�)
		//url = savedRequest.getRedirectUrl();
		
		url = "/login_success";
		
		redirectStrategy.sendRedirect(request, response, url);
		
		//super.onAuthenticationSuccess(request, response, authentication);
	}
}
