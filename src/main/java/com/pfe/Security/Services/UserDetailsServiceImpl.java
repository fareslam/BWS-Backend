package com.pfe.Security.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.Entity.SubUser;
import com.pfe.Repository.SubUserRepository;





@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	SubUserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		SubUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> 
				new UsernameNotFoundException("SubUser Not Found with username: "+ username));
			return UserDetailsImpl.build(user);
	}

}
