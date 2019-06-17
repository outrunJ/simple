package com.outrun.netty.discard.serverpojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.outrun.netty.discard.clientpojo.UnixTime;

public class TimeEncoder2 extends MessageToByteEncoder<UnixTime> {

	@Override
	protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
		out.writeInt((int)msg.value());
	}

}
