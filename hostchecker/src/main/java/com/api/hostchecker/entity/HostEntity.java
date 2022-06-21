package com.api.hostchecker.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "hosts")
public class HostEntity implements Serializable {

	private static final long serialVersionUID = -978321572403501403L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="host_seq")
	private long id;

	@Column(unique=true)
	private String name;

	@Column(unique=true)
	private String ip;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean isAlive;
	
	@Column()
	private LocalDateTime lastAliveTime;
	
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Boolean getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(Boolean isAlive) {
		this.isAlive = isAlive;
	}

	public LocalDateTime getLastAliveTime() {
		return lastAliveTime;
	}

	public void setLastAliveTime(LocalDateTime lastAliveTime) {
		this.lastAliveTime = lastAliveTime;
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
    
    
	
}
