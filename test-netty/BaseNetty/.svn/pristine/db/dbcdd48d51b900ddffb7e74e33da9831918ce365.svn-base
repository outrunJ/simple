package com.ryx.bank.base.server;

/**
 * @author Qi.shl 2014年10月14日下午2:05:04
 */
public class ServerLauncher {
	public static void main(String[] args) {
		int port = 8083;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);

		System.out.println("[Server] is listenning on port: " + port);
		BaseServer.run(port);
	}
}
