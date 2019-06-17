package com.ryx.bank.base.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Qi.shl 2014年10月14日下午3:05:05
 */
public class BaseClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("channelActive");
		
		ByteBuf buf = ctx.alloc().buffer();
		buf.writeBytes("Hello Server!".getBytes());
		
		ctx.writeAndFlush(buf);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("channelRead");
		
		ByteBuf buf = (ByteBuf) msg;
		byte[] bs = new byte[buf.readableBytes()];
		buf.readBytes(bs);
		
		System.out.println("--------------- Response: " + new String(bs));
		ReferenceCountUtil.release(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete");

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	}
}
