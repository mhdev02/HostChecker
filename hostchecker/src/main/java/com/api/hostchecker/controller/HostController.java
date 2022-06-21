package com.api.hostchecker.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hostchecker.dto.HostDto;
import com.api.hostchecker.model.request.HostRequestModel;
import com.api.hostchecker.model.response.HostRest;
import com.api.hostchecker.service.HostService;

@RestController
@RequestMapping("/hosts")
public class HostController {
	
	@Autowired
	HostService hostService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HostRest registerHost(@RequestBody HostRequestModel hostInfo) throws Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		HostDto hostDto = modelMapper.map(hostInfo, HostDto.class);

		HostDto createdHost = hostService.createHost(hostDto);
		HostRest returnValue = modelMapper.map(createdHost, HostRest.class);

		return returnValue;
	}
	
	@PutMapping(path = "/{ip}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public HostRest updateHost(@PathVariable String ip, @RequestBody HostRequestModel hostInfo) {

		ModelMapper modelMapper = new ModelMapper();

		HostDto hostDto = modelMapper.map(hostInfo, HostDto.class);

		HostDto updatedHost = hostService.updateHostByIp(hostDto.getIp(), hostDto);
		HostRest returnValue = modelMapper.map(updatedHost, HostRest.class);

		return returnValue;
	}

}
