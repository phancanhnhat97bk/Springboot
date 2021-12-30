package ait.nhatpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.nhatpc.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findOneByCode(String code);
}