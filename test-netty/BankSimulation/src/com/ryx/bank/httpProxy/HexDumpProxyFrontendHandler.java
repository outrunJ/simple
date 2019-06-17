/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.ryx.bank.httpProxy;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;

public class HexDumpProxyFrontendHandler extends ChannelInboundHandlerAdapter {

    private final String remoteHost;
    private final int remotePort;

    private volatile Channel outboundChannel;

    public HexDumpProxyFrontendHandler(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        final Channel inboundChannel = ctx.channel();

        // Start the connection attempt.
        Bootstrap b = new Bootstrap();
        b.group(inboundChannel.eventLoop())
         .channel(ctx.channel().getClass())
         .handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast("httpCodec", new HttpClientCodec());
						ch.pipeline().addLast("proxy", new HexDumpProxyBackendHandler(inboundChannel));
					}
				})
         .option(ChannelOption.AUTO_READ, false);
        ChannelFuture f = b.connect(remoteHost, remotePort);
        outboundChannel = f.channel();
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                try{
                	if (future.isSuccess()) {
                        // connection complete start to read first data
                        inboundChannel.read();
                    } else {
                        // Close the connection if the connection attempt has failed.
                        inboundChannel.close();
                    }
				} catch (Exception e) {
					System.err.println("java.io.IOException: Connection reset by peer");
				}
            }
        });
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) {
    	FullHttpRequest request = null;
    	if (msg instanceof HttpContent) {
			HttpContent httpContent = (HttpContent) msg;
			ByteBuf content = httpContent.content();
			if (content.isReadable()) {
				request = new DefaultFullHttpRequest(HTTP_1_1,
						HttpMethod.POST, "http://127.0.0.1:8080/lrun/netty/j8582/0200", content);

				request.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
				request.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
						request.content().readableBytes());
				msg = request;
				Counter.increment();
			}
			if (outboundChannel.isActive()) {
				outboundChannel.writeAndFlush(request).addListener(
					new ChannelFutureListener() {
						@Override
						public void operationComplete(ChannelFuture future) {
							try{
								if (future.isSuccess()) {
									// was able to flush out data, start to read
									// the next chunk
									ctx.channel().read();
								} else {
									future.channel().close();
								}
							} catch (Exception e) {
								System.err.println("java.io.IOException: Connection reset by peer");
							}
					}
				});
			}
		}
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (outboundChannel != null) {
            closeOnFlush(outboundChannel);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        closeOnFlush(ctx.channel());
    }

    /**
     * Closes the specified channel after all queued write requests are flushed.
     */
    static void closeOnFlush(Channel ch) {
        if (ch.isActive()) {
            // ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }
}
