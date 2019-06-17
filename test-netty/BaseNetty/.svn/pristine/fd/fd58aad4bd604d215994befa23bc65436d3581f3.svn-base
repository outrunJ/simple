package com.ryx.bank.http.server.handlers;

import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicInteger;

import com.ryx.bank.http.others.HttpHeaderUtil;

/**
 * @author Leo 2014年11月21日上午10:10:53
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

	private HttpRequest req = null;
	private FullHttpResponse response = null;
	private boolean keepAlive = false;
	
	private static final AtomicInteger count = new AtomicInteger(0);
	private static final AtomicInteger handledCount = new AtomicInteger(0);
	private void increment() {
		System.out.format("++++ Server cached: %d\n", count.incrementAndGet());
	}

	private void decrement() {
		// count.decrementAndGet();
		System.out.format("---- Server handled :%d, cached: %d\n", handledCount.incrementAndGet(), count.decrementAndGet());
	}
	
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		increment();
    }
	
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws UnsupportedEncodingException, ParseException {
		if (msg instanceof HttpRequest) {
			req = (HttpRequest) msg;

			if (HttpHeaderUtil.is100ContinueExpected(req))
				ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
		}

		if (msg instanceof HttpContent) {
			HttpContent httpContent = (HttpContent) msg;
			ByteBuf content = httpContent.content();
			if (content.isReadable()) {
				String reqContent = content.toString(CharsetUtil.UTF_8);
				System.out.println("-------------- Request: " + reqContent);
			}
		}
	}

	public void channelReadComplete(ChannelHandlerContext ctx) {
		System.out.println("channelReadComplete");
		response = this.getResponseMessage();
		if (!keepAlive) {
			ctx.write(response).addListener(ChannelFutureListener.CLOSE);
		} else {
			response.headers().set(HttpHeaders.Names.CONNECTION,
					HttpHeaders.Values.KEEP_ALIVE);
			ctx.write(response);
		}
		ctx.flush();
		decrement();
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	private FullHttpResponse getResponseMessage() {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
				Unpooled.wrappedBuffer("[HTTP] Hello Client!".getBytes()));
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
		response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
				response.content().readableBytes());

		return response;
	}
}
