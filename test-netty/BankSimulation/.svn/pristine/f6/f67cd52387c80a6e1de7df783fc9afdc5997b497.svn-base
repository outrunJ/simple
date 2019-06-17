package com.ryx.bank.base.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ryx.bank.utils.Functions;
import com.ryx.bank.utils.MessageHelper;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;

/**
 * 
 * @author Qi.shl 2014年10月14日下午3:05:05
 */
public class J8583ClientHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger
			.getLogger(J8583ClientHandler.class.getName());

	private IsoMessage isoMsg = null;

	/**
	 * Creates a client-side handler.
	 */
	public J8583ClientHandler() {
		MessageFactory<IsoMessage> mf = MessageHelper.getMessageFactory();
		isoMsg = mf.newMessage(0x200);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("channelActive");
		ctx.write(isoMsg);
		// ctx.write(isoMsg);
		// ctx.write(isoMsg);
		// ctx.write(isoMsg);
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("channelRead");
		isoMsg = (IsoMessage) msg;
		Functions.printJ8583Message("[Server Response] Msg Info :", isoMsg);
		ReferenceCountUtil.release(isoMsg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete");

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				cause);
		ctx.close();
	}
}
