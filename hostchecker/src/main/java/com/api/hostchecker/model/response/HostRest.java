package com.api.hostchecker.model.response;

import java.time.LocalDateTime;

public class HostRest {

	private String name;
	private String ip;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private String isAlive;
	private LocalDateTime lastAliveTime;

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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(String isAlive) {
		this.isAlive = isAlive;
	}

	public LocalDateTime getLastAliveTime() {
		return lastAliveTime;
	}

	public void setLastAliveTime(LocalDateTime lastAliveTime) {
		this.lastAliveTime = lastAliveTime;
	}

}
