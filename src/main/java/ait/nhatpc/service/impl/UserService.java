package ait.nhatpc.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ait.nhatpc.converter.UserConverter;
import ait.nhatpc.dto.UserDTO;
import ait.nhatpc.entity.UserEntity;
import ait.nhatpc.repository.UserRepository;
import ait.nhatpc.service.IUserService;

@Service
public class UserService implements IUserService, UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
	
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		if(userRepository.findByUserName(userDTO.getUserName()) == null) {
			UserEntity entity = new UserEntity();
			UserDTO dto = new UserDTO();
			entity = userConverter.toEntity(userDTO);
			entity.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
			entity = userRepository.save(entity);
			dto = userConverter.toDTO(entity);
			return dto;
		}
		return null;
	}

}
