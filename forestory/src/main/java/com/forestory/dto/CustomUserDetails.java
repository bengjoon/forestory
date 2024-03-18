package com.forestory.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.forestory.domain.User;

public class CustomUserDetails implements UserDetails{
	
	private User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}
	
	/** 
	 * 계정이 갖고있는 권한 목록을 리턴(권한이 여러개라면 루프해야겠지?)
	 * */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getUserRole();
			}
		});
		
		return collection;
	}

	@Override
	public String getPassword() {
		return user.getUserPw();
	}

	@Override
	public String getUsername() {
		return user.getUserEmail();
	}
	
	public long getUserNo() {
		return user.getUserNo();
	}
	
	public String getUserRegdate() {
		// yyyy-MM-dd
		String userRegdate = user.getUserRegdate().format(DateTimeFormatter.ISO_LOCAL_DATE);
		return userRegdate;
	}
	
	public String getUserNick() {
		return user.getUserNick();
	}
	
	public String getUserPhone() {
		return user.getUserPhone();
	}
	
	public LocalDateTime getUserLeavedate() {
		return user.getUserLeavedate();
	}
	
	public Boolean getUserState() {
		return user.getUserState();
	}
	
	/**
	 * 계정이 만료되지 않았는지 리턴(true:만료안됨)
	 * */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	
	/**
	 * 계정이 잠겨있지 않았는지 리턴(true:잠기지않음)
	 * */
	@Override
	public boolean isAccountNonLocked() {	
		return true;
	}
	
	
	/**
	 * 비밀번호가 만료되지 않았는지 리턴(true:만료안됨)
	 * */
	@Override
	public boolean isCredentialsNonExpired() {	
		return true;
	}
	
	
	/**
	 * 계정 활성화(사용가능)인지 리턴(true:사용가능)
	 * */
	@Override
	public boolean isEnabled() {	
		return true;
	}
	
}
