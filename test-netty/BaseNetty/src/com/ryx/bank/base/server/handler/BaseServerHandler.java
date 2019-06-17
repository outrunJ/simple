package com.ryx.bank.base.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Qi.shl 2014年10月14日下午2:43:22
 */
public class BaseServerHandler extends ChannelInboundHandlerAdapter {
	private static final Logger logger = Logger
			.getLogger(BaseServerHandler.class.getName());

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("channelActive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("channelRead");
		increment();
		
		ByteBuf buf = (ByteBuf) msg;
		byte[] bs = new byte[buf.readableBytes()];
		buf.readBytes(bs);
		
		System.out.println("--------------- Request: " + new String(bs));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete");

		ByteBuf buf = ctx.alloc().buffer();
		buf.writeBytes("Hello Client!".getBytes());
		ChannelFuture f = ctx.writeAndFlush(buf);

		f.addListener(ChannelFutureListener.CLOSE);
		decrement();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				cause);
		ctx.close();
	}

	private static final AtomicInteger count = new AtomicInteger(0);

	private void increment() {
		System.out.format("Online user %d\n", count.incrementAndGet());
	}

	private void decrement() {
		if (count.get() <= 0) {
			System.out.format("~Online user %d\n", 0);
		} else {
			System.out.format("~Online user %d\n", count.decrementAndGet());
		}
	}
}
