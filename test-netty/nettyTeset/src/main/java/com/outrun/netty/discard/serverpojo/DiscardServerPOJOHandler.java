package com.outrun.netty.discard.serverpojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.outrun.netty.discard.clientpojo.UnixTime;

public class DiscardServerPOJOHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ChannelFuture f = ctx.writeAndFlush(new UnixTime());
		f.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ((ByteBuf) msg).release();
		ByteBuf in = (ByteBuf) msg;
		try {
			// while (in.isReadable()) {
				// System.out.print((char) in.readByte());
				// System.out.flush();
			// }
			ctx.write(msg);
			ctx.flush();
			// System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII))
		} finally {
			// ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
