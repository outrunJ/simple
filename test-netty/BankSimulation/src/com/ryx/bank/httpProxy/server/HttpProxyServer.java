package com.ryx.bank.httpProxy.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.ryx.bank.httpProxy.HexDumpProxyInitializer;

/**
 * @author Leo 2014年11月10日下午2:45:08
 */
public class HttpProxyServer {
	 private static final int LOCAL_PORT = 8082;
	 private static final String REMOTE_HOST = "127.0.0.1";
	 private static final int REMOTE_PORT = 8083;

	public void run() {
		EventLoopGroup workerGroup = new NioEventLoopGroup(20);
		EventLoopGroup bossGroup = new NioEventLoopGroup(20);

		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.childOption(ChannelOption.ALLOCATOR,
				PooledByteBufAllocator.DEFAULT); // use direct buffers
		bootstrap.childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK,
				32 * 1024);
		bootstrap.childOption(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK,
				8 * 1024);
		bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new HexDumpProxyInitializer(REMOTE_HOST, REMOTE_PORT)) 
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.AUTO_READ, false)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
		;
		ChannelFuture f;
		try {
			System.err.println("[HTTP Proxy]http://127.0.0.1:" + LOCAL_PORT + "/ ; [Server] http://" + REMOTE_HOST + ":" + REMOTE_PORT);
			// Bind and start to accept incoming connections.
			f = bootstrap.bind(LOCAL_PORT).sync();
			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to
			// gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}

	}
}
