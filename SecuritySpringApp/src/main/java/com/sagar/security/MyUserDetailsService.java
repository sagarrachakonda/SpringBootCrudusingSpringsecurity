package com.sagar.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sagar.security.model.User;
import com.sagar.security.repository.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository Repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = Repo.findByUsername(username);
		
		if(user==null) {
			new UsernameNotFoundException("User Not found");
		}
		return new UserPrincipal(user);
	}

}
