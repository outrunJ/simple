package com.ryx.bank.https.client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo 2014年11月10日下午2:45:28
 */
public class ClientLauncher {
	public static final String serverIp = "127.0.0.1";
	public static final int serverPort = 8080;
	
	public static void main(String[] args) {
		List<HThread> tlist = new ArrayList<HThread>();
		long startTime = System.currentTimeMillis();
		
		HThread thread = null;
		for (int i = 0; i < 5; i ++) {
			thread = new HThread(i + 1);
			tlist.add(thread);
			thread.start();
			if (i > 200) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(thread.name + " started");
		}
		
		for (HThread t: tlist) {
			try {
				t.join();
				System.out.println(t.name + " Stopped--");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
	}
}

class HThread extends Thread {
	public String name = null;
	
	HThread(int idx) {
		name = "Thread-" + idx;
	}
	
	public void run() {
		new HttpsClient(ClientLauncher.serverIp, ClientLauncher.serverPort).run();
	}
}
