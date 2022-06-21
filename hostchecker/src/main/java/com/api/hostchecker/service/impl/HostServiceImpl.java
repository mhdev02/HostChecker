package com.api.hostchecker.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.hostchecker.common.Checker;
import com.api.hostchecker.dto.HostDto;
import com.api.hostchecker.entity.HostEntity;
import com.api.hostchecker.repository.HostRepository;
import com.api.hostchecker.service.HostService;

public class HostServiceImpl implements HostService {
	
	@Autowired 
	Checker checker;

	@Autowired
	HostRepository hostRepository;

	@Override
	public HostDto createHost(HostDto host) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HostDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HostDto getHost(String ip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HostDto updateHostByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HostDto updateHostByIp(String ip, HostDto host) {
		
		HostEntity hostEntity = hostRepository.findByIp(ip);
		
		if (hostEntity == null) {
			throw new RuntimeException();
		}
		
		hostEntity.setName(host.getName());
		hostEntity.setIp(host.getIp());
		
		String result = checker.aliveCheck(ip);
		if (result == "Alive") {
			hostEntity.setIsAlive(true);
			hostEntity.setLastAliveTime(LocalDateTime.now());
		} else if (result == "Unavailable") {
			hostEntity.setIsAlive(false);
		}
		
		HostEntity updatedHostEntity = hostRepository.save(hostEntity);

		HostDto returnValue = new ModelMapper().map(updatedHostEntity, HostDto.class);
		
		return returnValue;

	}

	@Override
	public void deleteHost(String ip) {
		// TODO Auto-generated method stub
		
	}
}
