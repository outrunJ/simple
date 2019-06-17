package com.ryx.bank.base.parsers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import com.ryx.bank.utils.Constants;
import com.ryx.bank.utils.MessageHelper;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;

/**
 * @author Leo 2014年10月24日上午10:55:03
 */
public class J8583MsgDecoder extends ByteToMessageDecoder {
	private MessageFactory<IsoMessage> msgFactory = MessageHelper
			.getMessageFactory();

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
			List<Object> out) throws Exception {
		// 若一次接收多个请求报文时，需要在每个报文前加该报文长度，然后根据长度读取并解析报文，不足一报文的需要放到下次循环中与再次接收的报文组成一次完整请求报文。
		// 因为ByteBuf的最大长度为1024，有可能一次含多个报文，也有可能一次不足一个报文
		System.out.println("decoding");
		while (msg.readableBytes() > Constants.ISOHEADERLENGTH) {
			System.out.println("ReadableBytes length : " + msg.readableBytes());
			byte[] msgBytes = new byte[msg.readableBytes()];
			msg.readBytes(msgBytes);
			IsoMessage isoMsg = msgFactory.parseMessage(msgBytes,
					Constants.ISOHEADERLENGTH);
			out.add(isoMsg);
		}
	}
}
