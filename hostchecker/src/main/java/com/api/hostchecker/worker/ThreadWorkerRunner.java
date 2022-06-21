// https://codechacha.com/ko/java-scheduled-thread-pool-executor/

package com.api.hostchecker.worker;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.api.hostchecker.dto.HostDto;
import com.api.hostchecker.service.HostService;

@Component
public class ThreadWorkerRunner implements ApplicationRunner {
	
	@Autowired
	HostService hostService;
	
	public void monitor() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
	    Runnable runnable = () -> {
	    	List<HostDto> hosts = hostService.getAll();
	    		for (HostDto host: hosts) {
	    			String ipAddr = host.getIp();
	    			hostService.updateHostByIp(ipAddr, host);
	    		}
	    };
	    int initialDelay = 0;
	    int delay = 0;

	    System.out.println("Monitoring started at : " + LocalTime.now());
	    executor.scheduleWithFixedDelay(
	        runnable, initialDelay , delay, TimeUnit.SECONDS);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		monitor();
	}
}