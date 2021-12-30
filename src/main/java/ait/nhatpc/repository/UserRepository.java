package ait.nhatpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.nhatpc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByUserName(String username);
}
