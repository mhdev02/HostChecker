// https://codechacha.com/ko/java-scheduled-thread-pool-executor/

package com.api.hostchecker.worker;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.hostchecker.dto.HostDto;
import com.api.hostchecker.service.HostService;

@Component
public class ThreadWorker {

	@Autowired
	HostService hostService;

	public void monitor() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		Runnable runnable = () -> {
			List<HostDto> hosts = hostService.getAll();
			if (hosts.size() > 0) {
				for (HostDto host : hosts) {
					String ipAddr = host.getIp();
					hostService.updateHostByIp(ipAddr, host);
				}
			}
		};
		int initialDelay = 1;
		int delay = 1;

		System.out.println("Monitoring started at : " + LocalTime.now());
		executor.scheduleWithFixedDelay(runnable, initialDelay, delay, TimeUnit.SECONDS);
	}

}