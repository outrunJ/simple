package com.ryx.bank.base.parsers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import com.solab.iso8583.IsoMessage;

/**
 * @author Leo 2014年10月24日上午10:52:56
 */
public class J8583MsgEncoder extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) {
		System.out.println("encoding");

		IsoMessage isoMsg = (IsoMessage) msg;

		String isoStr = isoMsg.debugString();
		byte[] msgBytes = isoStr.getBytes();
		System.out.println("IsoMessage length : " + msgBytes.length);

		ByteBuf encoded = ctx.alloc().buffer();
		encoded.writeBytes(msgBytes);
		ctx.write(encoded, promise);
	}
}
