package com.api.hostchecker.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.hostchecker.dto.HostDto;
import com.api.hostchecker.entity.HostEntity;
import com.api.hostchecker.repository.HostRepository;
import com.api.hostchecker.service.HostService;
import com.api.hostchecker.worker.AliveChecker;

public class HostServiceImpl implements HostService {
	
	@Autowired 
	AliveChecker checker;

	@Autowired
	HostRepository hostRepository;

	@Override
	public HostDto createHost(HostDto host) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HostDto> getAll() {
		List<HostDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		List<HostEntity> hosts = hostRepository.findAll();
		
		for (HostEntity host: hosts) {
			returnValue.add(modelMapper.map(host, HostDto.class));
		}
		return returnValue;
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
		if (result.equals("Alive")) {
			hostEntity.setIsAlive(true);
			hostEntity.setLastAliveTime(LocalDateTime.now());
		} else if (result.equals("Unavailable")) {
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
