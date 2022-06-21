package com.api.hostchecker.common;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

@Service
public class Checker {

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
			if (inetAddress.isReachable(500)) { // timeout 0.5s
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
