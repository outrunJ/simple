package com.ryx.bank.httpProxy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Leo
 * 2014年12月5日下午3:19:10
 */
public class Counter {
	private static final AtomicInteger count = new AtomicInteger(0);
	private static final AtomicInteger handledCount = new AtomicInteger(0);
	public static void increment() {
		System.out.format("++++ [Counter] Server cached: %d\n", count.incrementAndGet());
	}

	public static void decrement() {
		System.out.format("---- [Counter] Server handled :%d, cached: %d\n", handledCount.incrementAndGet(), count.decrementAndGet());
	}
}
