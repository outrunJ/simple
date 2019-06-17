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

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class HexDumpProxyInitializer extends ChannelInitializer<SocketChannel> {

    private final String remoteHost;
    private final int remotePort;

    public HexDumpProxyInitializer(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void initChannel(SocketChannel ch) {
    	ChannelPipeline pipeline = ch.pipeline();
		
		// pipeline.addLast("logger", new LoggingHandler(LogLevel.INFO));
		pipeline.addLast("idleDis", new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
		pipeline.addLast("compressor", new HttpContentCompressor());
		pipeline.addLast("decompressor", new HttpContentDecompressor());
		pipeline.addLast("serverCodec", new HttpServerCodec());
		// pipeline.addLast("httpHandler", new HttpServerHandler());
		// pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
        pipeline.addLast(new HexDumpProxyFrontendHandler(remoteHost, remotePort));
    }
}
