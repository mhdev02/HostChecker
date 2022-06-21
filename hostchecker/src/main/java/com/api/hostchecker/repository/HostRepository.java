package com.api.hostchecker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.hostchecker.entity.HostEntity;

public interface HostRepository extends CrudRepository<HostEntity, Long> {
	
	List<HostEntity> findAll();

	HostEntity findByIp(String ip);

}
