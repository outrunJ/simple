package com.ryx.bank.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;

/**
 * @author Leo 2014年10月14日下午3:36:42
 */
public final class MessageHelper {
	public static MessageFactory<IsoMessage> getMessageFactory() {
		final MessageFactory<IsoMessage> mf = new MessageFactory<IsoMessage>();
		final String url = System.getProperty("user.dir") + "/config/8583.xml";
		try {
			ConfigParser.configureFromUrl(mf, new File(url).toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mf;
	}
}
