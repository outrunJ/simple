package com.ryx.bank.http.client.handlers;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.CharsetUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ryx.bank.base.client.handler.BaseClientHandler;
import com.ryx.bank.http.client.ClientLauncher;

/**
 * @author Leo 2014年11月21日上午10:57:57
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter {
	private static final Logger logger = Logger
			.getLogger(BaseClientHandler.class.getName());

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("channelActive");

		ByteBufAllocator alloc = ctx.alloc();
		ByteBuf content = alloc.heapBuffer();
		content.writeBytes("[HTTP] Hello Server!".getBytes());

		FullHttpRequest request = new DefaultFullHttpRequest(HTTP_1_1,
				HttpMethod.POST, "http://" + ClientLauncher.serverIp + ":" + ClientLauncher.serverPort + "/netty/j8582/0200", content);
		request.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
		request.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
				request.content().readableBytes());
		
		ctx.write(request);
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("channelRead");
		if (msg instanceof HttpContent) {
			HttpContent httpContent = (HttpContent) msg;
			ByteBuf content = httpContent.content();
			if (content.isReadable()) {
				String reqContent = content.toString(CharsetUtil.UTF_8);
				System.out.println("----------------- Response: " + reqContent);
			}
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().close();
		System.out.println("channelReadComplete");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				cause);
		ctx.close();
	}
}
