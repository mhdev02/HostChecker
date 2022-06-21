package com.api.hostchecker.dto;

import java.io.Serializable;

public class HostDto implements Serializable {

	private static final long serialVersionUID = -2659212098742726279L;
	private String name;
	private String ip;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
