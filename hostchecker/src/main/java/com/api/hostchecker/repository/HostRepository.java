package com.api.hostchecker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.hostchecker.entity.HostEntity;

public interface HostRepository extends CrudRepository<HostEntity, Long> {

	List<HostEntity> findAll();

	HostEntity findByIp(String ip);

	HostEntity findByName(String name);

	@Query(value = "select * from hosts order by modified_date", nativeQuery = true)
	List<HostEntity> getAllOrderedByModifiedDate();

}