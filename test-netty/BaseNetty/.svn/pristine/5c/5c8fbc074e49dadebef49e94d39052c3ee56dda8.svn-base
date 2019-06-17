package com.ryx.bank.base.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.ryx.bank.base.server.handler.BaseServerHandler;

/**
 * @author Qi.shl 2014年10月14日下午2:20:24
 */
public class BaseServer {
	private int port = 0000;

	private BaseServer(int port) {
		this.port = port;
	}

	private void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(3);
		EventLoopGroup workerGroup = new NioEventLoopGroup(50);

		ServerBootstrap b = new ServerBootstrap();
		b.childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024);
		b.childOption(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 8 * 1024);
		b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						// ch.pipeline().addLast("logger", new LoggingHandler(LogLevel.INFO));
						ch.pipeline().addLast("handler",
								new BaseServerHandler());
					}
				});
		try {
			// Bind and start to accept incoming connections.
			ChannelFuture f = b.bind(port).sync();

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

	public static void run(int port) {
		new BaseServer(port).run();
	}
}
