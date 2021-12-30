package ait.nhatpc.converter;

import org.springframework.stereotype.Component;

import ait.nhatpc.dto.UserDTO;
import ait.nhatpc.entity.UserEntity;

@Component
public class UserConverter {
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setUserName(dto.getUserName());
		entity.setFullName(dto.getFullName());
		entity.setPassword(dto.getPassword());
		entity.setStatus(dto.getStatus());
		return entity;
	}

	public UserEntity toEntity(UserDTO dto, UserEntity entity) {
		entity.setUserName(dto.getUserName());
		entity.setFullName(dto.getFullName());
		entity.setPassword(dto.getPassword());
		entity.setStatus(dto.getStatus());
		return entity;
	}

	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setFullName(entity.getFullName());
		dto.setPassword(entity.getPassword());
		dto.setUserName(entity.getUserName());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setStatus(entity.getStatus());
		return dto;
	}
}
