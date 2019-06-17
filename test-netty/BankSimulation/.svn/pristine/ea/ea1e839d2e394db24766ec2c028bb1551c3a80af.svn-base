package com.ryx.bank.base.server.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ryx.bank.utils.Functions;
import com.ryx.bank.utils.MessageHelper;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;

/**
 * @author Qi.shl 2014年10月14日下午2:43:22
 */
public class J8583ServerHandler extends ChannelInboundHandlerAdapter {
	private static final Logger logger = Logger
			.getLogger(J8583ServerHandler.class.getName());

	private MessageFactory<IsoMessage> msgFactory = MessageHelper
			.getMessageFactory();
	private IsoMessage isoMsg = null;

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

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ctx.channel().config().setAutoRead(false);
		increment();
		System.out.println("channelRead");
		isoMsg = (IsoMessage) msg;
		Functions.printJ8583Message("[Client Request] Info :", isoMsg);
		ctx.channel().config().setAutoRead(true);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete");
		isoMsg = msgFactory.createResponse(isoMsg);

		ChannelFuture f = ctx.writeAndFlush(isoMsg);

		f.addListener(ChannelFutureListener.CLOSE);
		decrement();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				cause);
		ctx.close();
	}
}
