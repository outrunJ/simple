package com.outrun.netty.discard.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis() / 1000L + 2208988800L));
		final ChannelFuture f = ctx.writeAndFlush(time);
		// f.addListener(ChannelFutureListener.CLOSE);
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert f == future;
				ctx.close();
			}
		});
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
