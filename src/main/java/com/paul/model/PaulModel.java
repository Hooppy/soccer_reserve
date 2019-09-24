package com.paul.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class PaulModel implements UserDetails{
	String username;
	String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// 계정이 갖고 있는 권한 목록
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		if (username != null && username.equals("admin")) {
			authorities.add(new SimpleGrantedAuthority("USER_MANAGER"));
		}
		return authorities;
	}
	
	public boolean isAccountNonExpired() {
		return false;
	}
	public boolean isAccountNonLocked() {
		return false;
	}
	public boolean isCredentialsNonExpired() {
		return false;
	}
	public boolean isEnabled() {
		return false;
	}
}
