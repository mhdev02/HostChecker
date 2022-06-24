package com.api.hostchecker.worker;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

@Service
public class AliveChecker {

	public String aliveCheck(String ip) {
		String result1 = checkIfAvailable1(ip);
		String result2 = checkIfAvailable2(ip, 80, 1000);

		if (result1.equals("Alive") || result2.equals("Alive")) {
			return "Alive";
		} else
			return "Unavailable";
	}

	private String checkIfAvailable1(String ip) {
		InetAddress inetAddress = null;
		String result = "";
		try {
			inetAddress = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			if (inetAddress.isReachable(1000)) { // timeout 1s
				result = "Alive";

			} else {
				result = "Unavailable";

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// https://stackoverflow.com/questions/4779367/problem-with-isreachable-in-inetaddress-class
	private String checkIfAvailable2(String addr, int openPort, int timeOutMillis) {
		// Any Open port on other machine
		// openPort = 22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
		try {
			try (Socket soc = new Socket()) {
				soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
			}
			return "Alive";
		} catch (IOException ex) {
			return "Unavailable";
		}
	}

}
