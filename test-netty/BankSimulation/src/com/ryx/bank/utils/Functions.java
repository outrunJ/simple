package com.ryx.bank.utils;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoValue;

/**
 * @author Leo 2014年10月15日上午9:23:20
 */
public class Functions {

	public static void printJ8583Message(String prefix, IsoMessage msg) {
		System.out.println(prefix);
		printFeilds(msg);
	}

	public static void printFeilds(IsoMessage msg) {
		if (msg != null) {
			System.out.printf("Message type: %04x%n", msg.getType());
			System.out.println("FIELD TYPE    VALUE");
			for (int i = 2; i <= 128; i++) {
				IsoValue<?> f = msg.getField(i);
				if (f != null) {
					System.out.printf("%5d %-7s [", i, f.getType());
					System.out.print(f.toString());
					System.out.println(']');
				}
			}
		}
	}
}
