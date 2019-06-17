package com.ryx.bank.base.client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qi.shl 2014年10月14日下午2:04:50
 */
public class ClientLauncher {
	public static final String serverIp = "127.0.0.1";
	//public static final int serverPort = 8082;
	public static final int serverPort = 8083;

	static {
		System.out.println("[Client] is connect to " + serverIp + " : " + serverPort);
	}

	public static void main(String[] args) {
		try {
			
			List<ClientTread> alist = new ArrayList<ClientTread>();
			ClientTread at = null;
			
			long stime = System.currentTimeMillis();
			for (int i = 0; i < 300; i++) {
				at = new ClientTread(i);
				alist.add(at);
				at.start();
			}
			
			for (ClientTread ct: alist) {
				ct.join();
			}
			System.out.println(" Total consume = " + (System.currentTimeMillis() - stime));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println();
		}

	}
}

class ClientTread extends Thread {

	ClientTread(int idx) {
		this.setName("Thread-" + idx);
	}

	@Override
	public void run() {
		try {
			System.out.println(this.getName() + " running...");
			new J8583Client(ClientLauncher.serverIp, ClientLauncher.serverPort).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}