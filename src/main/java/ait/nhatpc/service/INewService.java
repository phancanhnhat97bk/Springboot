package ait.nhatpc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ait.nhatpc.dto.NewDTO;

public interface INewService {
	NewDTO save(NewDTO newDTO);
	void delete(long[] ids);
	List<NewDTO> findAll(Pageable pageable);
	List<NewDTO> findAll();
	int totalItem();
}