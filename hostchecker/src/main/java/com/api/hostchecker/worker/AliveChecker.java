package com.api.hostchecker.worker;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

@Service
public class AliveChecker {

	public String aliveCheck(String ip) {
		return check(ip);
	}

	private String check(String ip) {
		InetAddress inetAddress = null;
		String result = "";
		try {
			inetAddress = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			if (inetAddress.isReachable(1500)) { // timeout 1.5s
				result = "Alive";

			} else {
				result = "Unavailable";

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
