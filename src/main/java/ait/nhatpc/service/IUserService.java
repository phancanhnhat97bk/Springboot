package ait.nhatpc.service;

import org.springframework.security.core.userdetails.UserDetails;

import ait.nhatpc.dto.UserDTO;

public interface IUserService {

	UserDTO save(UserDTO userDTO);
	UserDetails loadUserByUsername(String username);
	
}
