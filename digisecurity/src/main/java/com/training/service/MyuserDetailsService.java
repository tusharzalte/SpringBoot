////package com.training.service;
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.Map;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.stereotype.Service; 
////
////@Service
////public class MyuserDetailsService implements UserDetailsService { 
////	private static final Map<String, String> users = new HashMap<>();
//	 static {
////		 users.put("tufail", "{noop}ahmed");
////		 users.put("neha", "{noop}a123");
////		 users.put("harish", "{noop}jaay");
////	 }
////	@Override
////	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////		String password = users.get(username);
////	    if (password == null) {
////	        throw new UsernameNotFoundException("User not found: " + username);
////	    }
////		User user =  new User(username, password,new ArrayList<>());
////		System.out.println("#### "+user);
////		return user;
////	}
////}
