package com.ryx.bank.base.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.ryx.bank.base.client.handler.BaseClientHandler;

/**
 * @author Qi.shl 2014年10月14日下午3:06:48
 */
public class BaseClient {
	private final String host;
	private final int port;

	public BaseClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws Exception {
		// Configure the client.
		EventLoopGroup group = new NioEventLoopGroup();

		Bootstrap b = new Bootstrap();
		b.option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024);
		b.option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 8 * 1024);
		b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						// ch.pipeline().addLast("logger", new LoggingHandler(LogLevel.INFO));
						ch.pipeline().addLast("handler",
								new BaseClientHandler());
					}
				});
		try {
			// Start the client.
			ChannelFuture f = b.connect(host, port).sync();

			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}
}
