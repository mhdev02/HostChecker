package com.api.hostchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.api.hostchecker.worker.ThreadWorker;

@EnableJpaAuditing
@SpringBootApplication
public class HostcheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostcheckerApplication.class, args);
	}

	@Autowired
	ThreadWorker threadWorker;

	@Bean
	CommandLineRunner startMonitor() {
		return args -> {
			threadWorker.monitor();
		};
	}

}