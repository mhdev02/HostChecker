package com.api.hostchecker.controller;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hostchecker.dto.HostDto;
import com.api.hostchecker.model.request.HostRequestModel;
import com.api.hostchecker.model.request.RequestName;
import com.api.hostchecker.model.response.HostRest;
import com.api.hostchecker.model.response.RequestStatus;
import com.api.hostchecker.model.response.StatusModel;
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

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<HostRest> getHosts() {
		List<HostRest> returnValue = new ArrayList<>();

		List<HostDto> hostDto = hostService.getAll();

		Type listType = new TypeToken<List<HostRest>>() {
		}.getType();
		returnValue = new ModelMapper().map(hostDto, listType);

		return returnValue;
	}

	@GetMapping(path = "/{ip}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public HostRest getHost(@PathVariable String ip) {

		ModelMapper modelMapper = new ModelMapper();

		HostDto hostDto = hostService.getHost(ip);

		HostRest returnValue = modelMapper.map(hostDto, HostRest.class);

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

	@DeleteMapping(path = "/{ip}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public StatusModel deleteItem(@PathVariable String ip) {
		StatusModel returnValue = new StatusModel();
		returnValue.setName(RequestName.DELETE.name());

		hostService.deleteHost(ip);

		returnValue.setResult(RequestStatus.SUCCESS.name());
		return returnValue;
	}

}
