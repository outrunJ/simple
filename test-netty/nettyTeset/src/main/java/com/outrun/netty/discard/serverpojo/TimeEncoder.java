package com.outrun.netty.discard.serverpojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import com.outrun.netty.discard.clientpojo.UnixTime;

public class TimeEncoder extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		UnixTime m = (UnixTime) msg;
		ByteBuf encoded = ctx.alloc().buffer(4);
		encoded.writeInt((int)m.value());
		ctx.write(encoded, promise);
		// 有override的flush方法来调用ctx.flush(), 所以这里不写
	}
	
}
