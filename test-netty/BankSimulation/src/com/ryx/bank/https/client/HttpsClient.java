package com.ryx.bank.https.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.ryx.bank.https.others.HttpChannelInitializer;

/**
 * @author Leo 2014年11月10日下午2:44:28
 */
public class HttpsClient {
	private String serverIp = null;
	private int serverPort = 8080;

	public HttpsClient(String host, int port) {
		this.serverIp = host;
		this.serverPort = port;
	}

	public void run() {
		EventLoopGroup group = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap();
		bootstrap.option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024);
		bootstrap.option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 8 * 1024);
		bootstrap.option(ChannelOption.ALLOCATOR,
				PooledByteBufAllocator.DEFAULT);

		bootstrap.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new HttpChannelInitializer(true));

		ChannelFuture f;
		try {
			// Start the client.
			f = bootstrap.connect(serverIp, serverPort).sync(); // (5)
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}
}
