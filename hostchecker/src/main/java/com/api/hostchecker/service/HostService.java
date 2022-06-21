package com.api.hostchecker.service;

import java.util.List;

import com.api.hostchecker.dto.HostDto;

public interface HostService {
	
	HostDto createHost(HostDto host);
	
	List<HostDto> getAll();

	HostDto getHost(String ip);
	
	HostDto updateHostByName(String name);
	
	HostDto updateHostByIp(String ip, HostDto hostDto);
	
	void deleteHost(String ip);
}
