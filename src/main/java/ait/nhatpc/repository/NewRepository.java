package ait.nhatpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ait.nhatpc.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {

}