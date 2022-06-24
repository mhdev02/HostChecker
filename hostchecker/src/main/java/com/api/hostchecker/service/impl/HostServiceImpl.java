package com.api.hostchecker.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hostchecker.dto.HostDto;
import com.api.hostchecker.entity.HostEntity;
import com.api.hostchecker.repository.HostRepository;
import com.api.hostchecker.service.HostService;
import com.api.hostchecker.worker.AliveChecker;

@Service
public class HostServiceImpl implements HostService {

	@Autowired
	AliveChecker aliveChecker;

	@Autowired
	HostRepository hostRepository;

	@Override
	public HostDto createHost(HostDto host) {

		if (hostRepository.findByIp(host.getIp()) != null) {
			throw new RuntimeException();
		}

		ModelMapper modelMapper = new ModelMapper();
		HostEntity hostEntity = modelMapper.map(host, HostEntity.class);
		
		String result = aliveChecker.aliveCheck(host.getIp());
		if (result.equals("Alive")) {
			hostEntity.setIsAlive("true");
			hostEntity.setLastAliveTime(LocalDateTime.now());
		} else if (result.equals("Unavailable")) {
			hostEntity.setIsAlive("false");
		}

		HostEntity savedHostEntity = hostRepository.save(hostEntity);

		HostDto returnValue = modelMapper.map(savedHostEntity, HostDto.class);

		return returnValue;
	}

	@Override
	public List<HostDto> getAll() {
		List<HostDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();

		List<HostEntity> hosts = hostRepository.findAll();

		for (HostEntity host : hosts) {
			returnValue.add(modelMapper.map(host, HostDto.class));
		}
		return returnValue;
	}
	
	@Override
	public List<HostDto> getAllOrderedByModifiedDate() {
		List<HostDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();

		List<HostEntity> hosts = hostRepository.getAllOrderedByModifiedDate();

		for (HostEntity host : hosts) {
			returnValue.add(modelMapper.map(host, HostDto.class));
		}
		return returnValue;
	}

	@Override
	public HostDto getHost(String ipOrName) {

		HostEntity hostEntity = hostRepository.findByIp(ipOrName);

		if (hostEntity == null) {
			hostEntity = hostRepository.findByName(ipOrName);
		}
		if (hostEntity == null) {
			throw new RuntimeException();
		}

		HostDto returnValue = new ModelMapper().map(hostEntity, HostDto.class);

		return returnValue;
	}

	@Override
	public HostDto updateHost(String ipOrName, HostDto host) {

		HostEntity hostEntity = hostRepository.findByIp(ipOrName);
		
		if (hostEntity == null) {
			hostEntity = hostRepository.findByName(ipOrName);
		}
		if (hostEntity == null) {
			throw new RuntimeException();
		}

		hostEntity.setName(host.getName());
		hostEntity.setIp(host.getIp());

		String result = aliveChecker.aliveCheck(host.getIp());
		if (result.equals("Alive")) {
			hostEntity.setIsAlive("true");
			hostEntity.setLastAliveTime(LocalDateTime.now());
		} else if (result.equals("Unavailable")) {
			hostEntity.setIsAlive("false");
		}

		HostEntity updatedHostEntity = hostRepository.save(hostEntity);

		HostDto returnValue = new ModelMapper().map(updatedHostEntity, HostDto.class);

		return returnValue;

	}
	
	@Transactional
	@Override
	public void deleteHost(String ipOrName) {
		HostEntity hostEntity = hostRepository.findByIp(ipOrName);
		
		if (hostEntity == null) {
			hostEntity = hostRepository.findByName(ipOrName);
		}

		if (hostEntity == null) {
			throw new RuntimeException();
		}

		hostRepository.delete(hostEntity);
	}
}
