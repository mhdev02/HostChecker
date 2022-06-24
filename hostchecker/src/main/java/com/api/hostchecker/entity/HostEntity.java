package com.api.hostchecker.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@Table(name = "hosts")
public class HostEntity extends BaseTimeEntity implements Serializable {

	private static final long serialVersionUID = -978321572403501403L;

	@Id
	@SequenceGenerator(name = "host_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "host_seq")
	private long id;

	@Column(unique = true)
	private String name;

	@Column(unique = true)
	private String ip;
	
	@Column()
	private String isAlive = "false";

	@Column()
	private LocalDateTime lastAliveTime;

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
