package com.ryx.bank.http.others;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import com.ryx.bank.http.client.handlers.HttpClientHandler;
import com.ryx.bank.http.server.handlers.HttpServerHandler;

/**
 * @author Leo 2014年11月11日下午4:05:28
 */
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {
	private final SSLContext sslCtx;
	private boolean isClient = false;
	
	public HttpChannelInitializer(boolean isClient) {
		this(null, isClient);
	}
	
	public HttpChannelInitializer(SSLContext sslCtx, boolean isClient) {
		this.sslCtx = sslCtx;
		this.isClient = isClient;
	}
	
	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		
		if (this.sslCtx != null) {
			SSLEngine engine = sslCtx.createSSLEngine();
			engine.setUseClientMode(this.isClient);
			pipeline.addFirst("ssl", new SslHandler(engine));
		}
		
		// pipeline.addLast("logger", new LoggingHandler(LogLevel.INFO));
		pipeline.addLast("idleDis", new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
		pipeline.addLast("compressor", new HttpContentCompressor());
		pipeline.addLast("decompressor", new HttpContentDecompressor());
		if (this.isClient) {
			pipeline.addLast("clientCodec", new HttpClientCodec());
			pipeline.addLast("httpHandler", new HttpClientHandler());
		} else {
			pipeline.addLast("serverCodec", new HttpServerCodec());
			pipeline.addLast("httpHandler", new HttpServerHandler());
		}
		// pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
	}
}
