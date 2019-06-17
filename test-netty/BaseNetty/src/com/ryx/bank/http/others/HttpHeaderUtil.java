package com.ryx.bank.http.others;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

/**
 * @author Leo 2014年11月21日上午10:17:19
 */
public class HttpHeaderUtil {

	public static boolean is100ContinueExpected(HttpMessage message) {
		// Expect: 100-continue is for requests only.
		if (!(message instanceof HttpRequest)) {
			return false;
		}

		// It works only on HTTP/1.1 or later.
		if (message.getProtocolVersion().compareTo(HttpVersion.HTTP_1_1) < 0) {
			return false;
		}

		// In most cases, there will be one or zero 'Expect' header.
		String value = message.headers().get(HttpHeaders.Names.EXPECT);
		if (value == null) {
			return false;
		}
		if (HttpHeaders.Values.CONTINUE.equalsIgnoreCase(value)) {
			return true;
		}

		// Multiple 'Expect' headers. Search through them.
		return message.headers().contains(HttpHeaders.Names.EXPECT,
				HttpHeaders.Values.CONTINUE, true);
	}

	public static boolean isKeepAlive(HttpMessage message) {
		String connection = message.headers().get(HttpHeaders.Names.CONNECTION);
		if (connection != null
				&& HttpHeaders.Values.CLOSE.equalsIgnoreCase(connection)) {
			return false;
		}

		if (message.getProtocolVersion().isKeepAliveDefault()) {
			return !HttpHeaders.Values.CLOSE.equalsIgnoreCase(connection);
		} else {
			return HttpHeaders.Values.KEEP_ALIVE.equalsIgnoreCase(connection);
		}
	}
}
