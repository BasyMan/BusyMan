package com.todolist.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todolist.model.User;
import com.todolist.repository.UserRepository;

import javax.annotation.Resource;

@Service
public class CustomAuthenticationProviderService implements AuthenticationProvider, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Resource
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authenticationToken = null;
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		User user = userRepository.findByUsername(username);
		if(user != null) {
			if(username.equals(user.getUserName()) && BCrypt.checkpw(password, user.getPassword())) {
				Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
				authenticationToken = new UsernamePasswordAuthenticationToken(
						new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities), password, grantedAuthorities);
			}
		} else {
			throw new UsernameNotFoundException("User name " + username + " not found");
		}
		return authenticationToken;
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if(user.getRole().getName().equals("admin")) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return grantedAuthorities;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user != null && user.getId() != null) {
			return new org.springframework.security.core.userdetails.User
					(username, passwordEncoder.encode(user.getPassword()), AuthorityUtils.NO_AUTHORITIES);
		}

		return null;
	}
}
